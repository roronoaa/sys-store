package cn.tedu.store.mapper;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 用户模块的持久层接口
 */
@Repository
public interface UserMapper {

    /**
     * 基于用户id更新用户头像路径
     * @param uid
     * @param avatar 头像路径
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatar(@Param("uid")Integer uid,
                         @Param("avatar")String avatar,
                         @Param("modifiedUser")String modifiedUser,
                         @Param("modifiedTime")Date modifiedTime);


    /**
     * 基于用户名更新用户信息
     * @param user
     * @return
     */
    Integer updateUserInfo(UserEntity user);

    /**
     * 基于用户id查询用户信息
     * 返回密码、盐值、是否删除
     * @param uid
     * @return
     */
    UserEntity findByUid(Integer uid);

    /**
     * 基于用户id更新用户密码
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePassword(@Param("uid") Integer uid,
                           @Param("password")String password,
                           @Param("modifiedUser")String modifiedUser,
                           @Param("modifiedTime")Date modifiedTime);

    /**
     * 添加一条用户记录
     * @param user
     * @return 添加成功的数据行数
     */
    Integer insertUser(UserEntity user);

    /**
     * 基于用户名查询用户记录
     * @param username
     * @return
     */
    UserEntity getByUsername(String username);

    List<Product> queryFavoriteById(Integer uid);

    Integer insertFavorite(@Param("uid") Integer uid,@Param("pid") Integer pid);

    Integer deleteFavorite(@Param("uid") Integer uid,@Param("pid") Integer pid);

    Integer findFavorite(@Param("uid")Integer uid,@Param("pid")Integer pid);

    Integer addPoint(@Param("uid")Integer uid,@Param("point")Integer point);

    Integer updateImageByUid(@Param("uid")Integer uid,@Param("iamges") String images);

    String queryUserEmailByUserName(String username);

    Integer updateUserPassword(@Param("username")String username,@Param("password") String password);

    UserEntity findByUserName(String username);
}
