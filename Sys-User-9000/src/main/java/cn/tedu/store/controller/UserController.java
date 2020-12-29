package cn.tedu.store.controller;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.ex.*;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService service;

    private static final long AVATAR_MAX_SIZE=600*1024;

    private static final List<String> AVATAR_TYPES=new ArrayList<>();

    // 静态初始化器：用于初始化本类的静态成员
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
    }

    @PostMapping("/changeAvatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,
                                           HttpServletRequest request, HttpSession session){

        // 空文件验证
        if(file.isEmpty()) {
            throw new FileEmptyException("文件上传异常！文件不能为空");
        }
        // 文件大小验证
        long fileSize=file.getSize();
        if(fileSize>AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件上传异常！文件大小超过上限:"+(AVATAR_MAX_SIZE/1024)+"kb");
        }

        // 文件类型验证
        if(!AVATAR_TYPES.contains(file.getContentType())) {
            throw new FileTypeException("文件上传异常！文件类型不正确，允许的类型有："+AVATAR_TYPES);
        }

        // 生成文件名-> 直接使用uuid+原文件名后缀
        String oFilename=file.getOriginalFilename();
        Integer index=oFilename.lastIndexOf(".");
        String suffix="";
        if(index!=-1) {
            suffix=oFilename.substring(index);
        }
        String filename= UUID.randomUUID().toString()+suffix;
        // 生成目标路径
        String filePath=request.getServletContext().getRealPath("upload");
        System.out.println(filePath);
        File parent=new File(filePath);
        if(!parent.exists()) {
            parent.mkdirs();//创建对应的目录
        }

        File dest=new File(parent,filename);
        // 将用户上传的头像保存到服务器上
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            throw new FileStateException("文件上传异常！"+e.getMessage());
            // throw new FileStateException("文件上传异常！"+e.getMessage(),e);
        } catch (IOException e) {
            throw new FileIOException("文件上传异常！"+e.getMessage());
        }

        // 将头像在服务器的路径保存到数据库
        String avatar="/upload/"+filename;
        UserEntity user=(UserEntity) session.getAttribute("user");
        Integer uid=user.getUid();
        System.out.println("--------------------" + user + "   " + uid);
        String username=user.getUsername();
        service.changeAvatar(uid,avatar,username);
        return new JsonResult<String>(1000,"OK", "http://localhost:9000/" + avatar);
    }

    @RequestMapping("/findUserInfo")
    public JsonResult<UserEntity> findUserInfo(HttpSession session,HttpServletRequest httpServletRequest){
        UserEntity user=(UserEntity)session.getAttribute("user");
        System.out.println(user);
        System.out.println("::"+httpServletRequest.getHeader("Origin"));
        return new JsonResult<>(1000,"OK",user);
    }

    @PostMapping("/changeUserInfo")
    public JsonResult<Void> changeUserInfo(UserEntity user, HttpSession session){
        // 调用持久层方法，更新用户信息
        service.changeUserInfo(user);
        // 更新session中的用户信息
        session.setAttribute("user",user);
        return new JsonResult<>(1000,"OK");
    }

    @PostMapping("/changePassword")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.changePassword(user.getUid(),oldPassword,newPassword,user.getUsername());
        return new JsonResult<>(1000,"OK");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session=req.getSession();
        // 清空Session中的数据，销毁session
        session.invalidate();
        // 删除自动登录的Cookie
        Cookie cookie=new Cookie("autoLogin","");
        // 设置cookie的有效时间
        cookie.setMaxAge(0); // 删除cookie
        // 设置Cookie的路径
        cookie.setPath("/");
        // 发送cookie
        resp.addCookie(cookie);
        // 重定向到登录页面
      //  resp.sendRedirect("/web/login.html");
        return null;
    }

    @PostMapping("/login")
    public JsonResult<UserEntity> login(String username, String password,
                                        String checked, HttpServletRequest req, HttpServletResponse resp){
        UserEntity user=service.login(username,password);
        HttpSession session=req.getSession();
        String md5Pwd=user.getPassword();
        user.setPassword(null); // 会话状态中密码置null
        // 向Session添加用户的登录状态
        session.setAttribute("user",user);
        // 执行自动登录的逻辑
        // 判断是否勾选了自动登录
//        if("true".equals(checked)){ // 勾选
//            Cookie cookie=new Cookie("autoLogin",username+"_#_"+md5Pwd);
//            // 设置cookie的有效时间
//            cookie.setMaxAge(24*60*60); // 24小时，可读性好
//            // 一般不主动设置Cookie的domain，因为可能导致被浏览器拒绝
//            // 设置Cookie的路径
//            cookie.setPath("/");
//            // 发送cookie
//            resp.addCookie(cookie);
//        }else{ // 未勾选
//            // 删除自动登录Cookie
//            Cookie cookie=new Cookie("autoLogin","");
//            // 设置cookie的有效时间
//            cookie.setMaxAge(0); // 删除cookie
//            // 设置Cookie的路径
//            cookie.setPath("/");
//            // 发送cookie
//            resp.addCookie(cookie);
//        }

        return new JsonResult<>(1000,"OK",user);
    }

    @RequestMapping("/checkUsername")
    public JsonResult<Void> checkUsername(String username){
        boolean flag=service.checkUsername(username);
        return new JsonResult<>(1000,flag+"");
    }

    @PostMapping("/regist")
    public JsonResult<Void> regist(UserEntity user){
        service.regist(user);
        return new JsonResult<Void>(1000,"OK");
    }

    @GetMapping("/queryUserById")
    public UserEntity queryByUid(Integer uid) {
        return service.queryByUid(uid);
    }

    /**
     * 查询收藏
     * @param session
     * @return
     */
    @GetMapping("/queryFavoriteById")
    public JsonResult<List<Product>> queryFavoriteById(HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        System.out.println(user);
        return new JsonResult<List<Product>>(1000,"OK",service.queryFavoriteById(user.getUid()));
    }

    /**
     * 添加收藏
     * @param session
     * @param pid
     * @return
     */
    @PostMapping("/addFavorite")
    public JsonResult<Void> addFavorite(HttpSession session,Integer pid){
        UserEntity user=(UserEntity)session.getAttribute("user");
        System.out.println(user);
        service.addFavorite(user.getUid(),pid);
        return new JsonResult<Void>(1000,"OK");
    }

    /**
     * 删除收藏
     * @param session
     * @param pid
     * @return
     */
    @PostMapping("/deleteFavorite")
    public JsonResult<Void> deleteFavorite(HttpSession session,Integer pid){
        service.deleteFavorite(((UserEntity)session.getAttribute("user")).getUid(),pid);
        return new JsonResult<Void>(1000,"OK");
    }

    /**
     * 判断是否收藏
     * @param session
     * @param pid
     * @return
     */
    @GetMapping("/isFavorite")
    public JsonResult<Integer> isFavorite(HttpSession session,Integer pid){
        UserEntity user=(UserEntity)session.getAttribute("user");
        return JsonResult.getSuccessJR(service.isFavorite(user.getUid(),pid));
    }

}
