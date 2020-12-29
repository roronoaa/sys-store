package cn.tedu.store.service;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.ex.EmptyArgumentException;
import cn.tedu.store.ex.InsertException;
import cn.tedu.store.ex.QueryException;
import cn.tedu.store.ex.UpdateException;
import cn.tedu.store.service.ex.*;
import cn.tedu.store.util.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户模块的业务层接口
 */
public interface IUserService {

    /**
     * 基于用户id查询用户信息
     * @param uid
     * @return
     */
    UserEntity queryByUid(Integer uid);

    /**
     * 基于用户id修改用户头像路径
     * @param uid
     * @param avatar
     * @param username
     * @throws EmptyArgumentException
     * @throws UpdateException
     */
    void changeAvatar(Integer uid,String avatar,String username)
            throws EmptyArgumentException, UpdateException;

    /**
     * 基于用户名修改用户信息
     * @param user
     * @throws EmptyArgumentException
     * @throws ChangeUserInfoException
     * @throws UpdateException
     */
    void changeUserInfo(UserEntity user) throws
            EmptyArgumentException, ChangeUserInfoException, UpdateException;

    /**
     * 基于用户id修改用户密码
     * @param uid
     * @param oldPassword
     * @param newPassword
     * @param modifiedUser
     * @throws EmptyArgumentException
     * @throws ChangePasswordException
     * @throws UpdateException
     */
    void changePassword(Integer uid,String oldPassword,
                        String newPassword,String modifiedUser)
            throws EmptyArgumentException, ChangePasswordException, UpdateException;

    /**
     * 自动登录验证用户名和密码
     * @param username
     * @param md5Pwd
     * @return
     * @throws EmptyArgumentException
     * @throws LoginException
     */
    UserEntity autoLogin(String username,String md5Pwd)
            throws EmptyArgumentException, LoginException;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 封装了用户信息的对象 或 null
     * @throws EmptyArgumentException
     * @throws LoginException
     */
    UserEntity login(String username,String password)
            throws EmptyArgumentException, LoginException;

    /**
     * 检查用户名是否存在
     * @param username
     * @return true-已存在 false-不存在
     * @throws EmptyArgumentException
     * @throws QueryException
     */
    boolean checkUsername(String username)
            throws EmptyArgumentException, QueryException;

    /**
     * 用户注册
     * @param user 用户注册信息
     * @throws EmptyArgumentException
     * @throws UserExistException
     * @throws InsertException
     */
    void regist(UserEntity user)
            throws EmptyArgumentException, UserExistException,
            InsertException;

    /**
     * 查询用户收藏商品
     * @param uid
     * @return 用户收藏的商品
     */
    List<Product> queryFavoriteById(Integer uid);

    /**
     * 添加收藏
     * @param uid
     * @param pid
     */
    void addFavorite(Integer uid,Integer pid);
    /**
     * 用户删除收藏
     * @param uid
     * @param pid
     */
    void deleteFavorite(Integer uid,Integer pid);
    Integer isFavorite(Integer uid,Integer pid);
    Integer addPoint(Integer uid,Integer point);
}
