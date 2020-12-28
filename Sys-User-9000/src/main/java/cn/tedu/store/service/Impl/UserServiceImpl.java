package cn.tedu.store.service.Impl;

import cn.tedu.store.entity.Product;
import cn.tedu.store.ex.*;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.*;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    public static final int HASH_TIME=5;

    @Autowired(required = false)
    UserMapper mapper;

    @Override
    public UserEntity queryByUid(Integer uid) {
        return mapper.findByUid(uid);
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) throws EmptyArgumentException, UpdateException {
        // 非空验证 TODO
        // 基于用户名查询用户信息
        UserEntity exist=null;
        try{
            exist=mapper.getByUsername(username);
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateException("修改头像异常："+e.getMessage(),e);
        }
        // 验证用户信息是否有效
        if(exist==null || exist.getIsDelete()==1){
            throw new UpdateException("修改头像异常：用户不存在或已删除");
        }
        // 更新用户信息
        int row=0;
        try{
            System.out.println("uid: " + uid);
            row=mapper.updateAvatar(uid,avatar,username,new Date());
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateException("修改头像异常："+e.getMessage(),e);
        }
        // 判断返回值结果是否不为1
        if(row!=1){
            // 是：UpdateException
            throw new UpdateException("修改头像异常：数据更新失败");
        }
    }

    @Override
    public void changeUserInfo(UserEntity user) throws EmptyArgumentException, ChangeUserInfoException, UpdateException {
        // 非空验证 TODO
        // 基于用户名查询用户信息
        UserEntity exist=null;
        try{
            exist=mapper.getByUsername(user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            throw new ChangeUserInfoException("修改用户信息异常："+e.getMessage(),e);
        }
        // 验证用户信息是否有效
        if(exist==null || exist.getIsDelete()==1){
            throw new ChangeUserInfoException("修改用户信息异常：用户不存在或已删除");
        }
        // 添加日志字段
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        // 更新用户信息
        int row=0;
        try{
            row=mapper.updateUserInfo(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new ChangeUserInfoException("修改用户信息异常："+e.getMessage(),e);
        }
        // 判断返回值结果是否不为1
        if(row!=1){
            // 是：UpdateException
            throw new ChangePasswordException("修改用户信息异常：数据更新失败");
        }
    }

    @Override
    public void changePassword(Integer uid, String oldPassword,
                               String newPassword, String modifiedUser)
            throws EmptyArgumentException, ChangePasswordException, UpdateException {
        // 非空验证 略
        // 基于uid查询用户信息
        UserEntity user=null;
        try{
            user=mapper.findByUid(uid);
        }catch (Exception e){
            e.printStackTrace();
            throw new ChangePasswordException("修改密码异常："+e.getMessage(),e);
        }
        // 判断是否没查询到信息
        if(user==null){
            // 是：ChangePasswordException
            throw new ChangePasswordException("修改密码异常：该用户不存在");
        }

        // 判断用户是否已被标记为删除
        if(user.getIsDelete()==1){
            // 是：ChangePasswordException
            throw new ChangePasswordException("修改密码异常：该用户不存在");
        }

        // 判断新旧密码是否一致
        if(oldPassword.equals(newPassword)){
            // 是：ChangePasswordException
            throw new ChangePasswordException("修改密码异常：新密码不能与旧密码一致");
        }

        // 基于查询到的盐值对用户传入的旧密码进行加密
        String oldMD5Pwd=getMD5Password(oldPassword,user.getSalt(),HASH_TIME);
        // 判断加密后的旧密码与查询到的密码是否不一致
        if(!oldMD5Pwd.equals(user.getPassword())){
            // 是：ChangePasswordException
            throw new ChangePasswordException("修改密码异常：旧密码错误");
        }

        // 基于查询到的盐值对新密码进行加密
        String newMD5Pwd=getMD5Password(newPassword,user.getSalt(),HASH_TIME);
        int row=0;
        try{
            // 调用持久层方法，更新密码
            row=mapper.updatePassword(uid,newMD5Pwd,modifiedUser,new Date());
        }catch (Exception e){
            e.printStackTrace();
            throw new ChangePasswordException("修改密码异常："+e.getMessage(),e);
        }
        // 判断返回值结果是否不为1
        if(row!=1){
            // 是：UpdateException
            throw new ChangePasswordException("修改密码异常：新密码设置失败");
        }
    }

    @Override
    public UserEntity autoLogin(String username, String md5Pwd) throws EmptyArgumentException, LoginException {
        // 参数验证
        if(StringUtils.isEmpty(username)){
            throw new EmptyArgumentException("自动登录异常：用户名不能为空");
        }
        if(StringUtils.isEmpty(md5Pwd)){
            throw new EmptyArgumentException("自动登录异常：密码不能为空");
        }
        // 基于用户名查用户数据
        UserEntity user=null;
        try{
            user=mapper.getByUsername(username);
        }catch (Exception e){
            e.printStackTrace();
            throw new LoginException("自动登录异常："+e.getMessage(),e);
        }
        // 查不到 -> 登录失败->抛异常 LoginException
        if(user==null){
            throw new LoginException("自动登录异常：用户名或密码错误");
        }
        // 判断用户是否已被删除 -> 删除则不需要验证密码
        if(user.getIsDelete().equals(1)){
            throw new LoginException("自动登录异常：用户名或密码错误");
        }
        // 判断密码
        if(!md5Pwd.equals(user.getPassword())){
            throw new LoginException("自动登录异常：用户名或密码错误");
        }

        // 将敏感信息(密码、盐值)置null
        user.setPassword(null);
        user.setSalt(null);
        // 返回userEntity对象
        return user;
    }

    @Override
    public UserEntity login(String username, String password)
            throws EmptyArgumentException, LoginException {
        // 参数验证
        if(StringUtils.isEmpty(username)){
            throw new EmptyArgumentException("登录异常：用户名不能为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new EmptyArgumentException("登录异常：密码不能为空");
        }
        // 基于用户名查用户数据
        UserEntity user=null;
        try{
            user=mapper.getByUsername(username);
        }catch (Exception e){
            e.printStackTrace();
            throw new LoginException("登录异常："+e.getMessage(),e);
        }
        // 查不到 -> 登录失败->抛异常 LoginException
        if(user==null){
            throw new LoginException("登录异常：用户名或密码错误");
        }
        // 判断用户是否已被删除 -> 删除则不需要验证密码
        if(user.getIsDelete().equals(1)){
            throw new LoginException("登录异常：用户名或密码错误");
        }

        // 对用户传入的密码进行加密
        String salt=user.getSalt();
        String md5Pwd=getMD5Password(password,salt,HASH_TIME);
        // 比对两个密码
        if(!md5Pwd.equals(user.getPassword())){
            // 不一致 -> 抛异常 LoginException
            throw new LoginException("登录异常：用户名或密码错误");
        }

        // 将敏感信息(密码、盐值)置null
        // user.setPassword(null);
        user.setSalt(null);
        // 返回userEntity对象
        return user;
    }

    @Override
    public boolean checkUsername(String username) {
        if(StringUtils.isEmpty(username)){
            throw new EmptyArgumentException("检查用户名异常：用户名不能为空");
        }
        boolean flag=false;
        try{
            UserEntity user=mapper.getByUsername(username);
            if(user!=null){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new QueryException("检查用户名异常："+e.getMessage(),e);
        }
        return flag;
    }

    @Override
    public void regist(UserEntity user)
            throws EmptyArgumentException, UserExistException,
            InsertException {
        // 判断user是否为null
        if(user==null){
            // 是：EmptyArgumentException
            throw new EmptyArgumentException("注册异常：用户信息为空");
        }

        // 判断用户名或密码是否为空
        if(StringUtils.isEmpty(user.getUsername())){
            // 是：EmptyArgumentException
            throw new EmptyArgumentException("注册异常：用户名为空");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            // 是：EmptyArgumentException
            throw new EmptyArgumentException("注册异常：密码为空");
        }

        // 判断用户名是否存在
        UserEntity entity=null;
        try{
            entity=mapper.getByUsername(user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            throw new InsertException("注册异常：查询用户名异常",e);
        }
        if(entity!=null){
            // 是：UserExistException
            throw new UserExistException("注册异常：用户名已存在");
        }

        // 对密码进行加密
        // 生成盐值
        String salt= UUID.randomUUID().toString();
        // 基于盐值对密码进行加密
        String md5Password=getMD5Password(user.getPassword(),salt,HASH_TIME);
        // 将加密信息添加到user中
        user.setPassword(md5Password);
        user.setSalt(salt);

        // 将user中的is_delete设为0
        user.setIsDelete(0);
        // 向user中设置createdUser和modifiedUser
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        // 调用持久层方法，添加用户数据
        int row=0;
        try{
            row=mapper.insertUser(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new InsertException("注册异常：添加用户信息异常",e);
        }
        // 判断返回值是否不为1
        if(row!=1){
            // 是：InsertException
            throw new InsertException("注册异常：添加用户信息失败");
        }
    }

    /**
     * 查询用户收藏的商品
     * @param uid
     * @return
     */
    public List<Product> queryFavoriteById(Integer uid){
        try{
            return mapper.queryFavoriteById(uid);
        }catch(Exception e){
            e.printStackTrace();
            throw new InsertException("查询异常：查询收藏信息失败",e);
        }

    }

    /**
     * 用户添加收藏
     * @param uid
     * @param pid
     * @return
     */
    public void addFavorite(Integer uid,Integer pid){
        int row=0;
        try{
            row=mapper.insertFavorite(uid,pid);
        }catch(Exception e){
            e.printStackTrace();
            throw new InsertException("收藏异常：添加收藏信息失败",e);
        }
        if(row!=1){
            throw new InsertException("收藏异常：添加收藏信息失败");
        }
    }

    /**
     * 用户收藏收藏
     * @param uid
     * @param pid
     */
    public void deleteFavorite(Integer uid,Integer pid){
        int row=0;
        try{
            row=mapper.deleteFavorite(uid,pid);
            System.out.println(row);
        }catch(Exception e){
            e.printStackTrace();
            throw new DeleteException("删除异常：删除收藏信息失败",e);
        }
        if(row==0){
            throw new DeleteException("删除异常：删除收藏信息失败");
        }
    }
    /**
     * 对密码进行加密
     * @param password 原始密码
     * @param salt 盐值
     * @param time hash迭代次数
     * @return 加密后的密码
     */
    public String getMD5Password(String password,String salt,int time){
        // 组合明文和盐值
        password=password+salt+password;
        // 进行hash迭代
        for(int i=1;i<=time;i++){
            password= DigestUtils.md5DigestAsHex(password.getBytes());
        }
        return password;
    }

}