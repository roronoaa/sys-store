package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {

    /**
     * 基于用户id查询用户最后更新的一条收货地址记录
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);

    /**
     * 基于地址id删除一条地址记录
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);

    /**
     * 基于地址id将地址设为默认收货地址
     * @param aid
     * @param username
     * @param modifiedTime
     * @return
     */
    Integer updateDefault(
            @Param("aid") Integer aid,
            @Param("username") String username,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 将该用户所有收货地址设为默认收货地址
     * @param uid
     * @return
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 基于aid查询收货地址记录
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     * 基于用户id查询收货地址
     * @param uid
     * @return
     */
    List<Address> listByUid(Integer uid);

    /**
     * 保存一条收货地址记录
     * @param address
     * @return
     */
    Integer saveAddress(Address address);

    /**
     * 基于用户id查询收货地址数量
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

}
