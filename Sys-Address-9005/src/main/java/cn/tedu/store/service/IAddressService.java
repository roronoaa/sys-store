package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.ex.*;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;

import java.util.List;

/**
 * 地址模块的业务层接口
 */
public interface IAddressService {

    /**
     * 基于id查询收货地址
     * @param aid
     * @return
     */
    Address findByAid(Integer aid)
            throws RecordNotFoundException;

    /**
     * 基于地址id删除地址记录
     * 如果删除的是默认默认收货地址，如果：
     * 1. 当前用户还有其他收货地址，则设置最后更新的地址为默认收货地址
     * 2. 当前用户没有其他收货地址，则不进行其他操作
     *
     * @param aid
     * @param uid
     * @param username
     * @throws AddressNotFoundException
     * @throws AccessDeniedException
     * @throws DeleteException
     * @throws UpdateException
     */
    void removeAddress(Integer aid,Integer uid, String username)
            throws AddressNotFoundException, AccessDeniedException, DeleteException, UpdateException;

    /**
     * 基于aid将用户的收货地址设为默认收货地址
     * @param aid
     * @param uid
     * @param username
     * @throws AddressNotFoundException
     * @throws AccessDeniedException
     * @throws UpdateException
     */
    void setDefault(Integer aid,Integer uid,String username)
            throws AddressNotFoundException, AccessDeniedException, UpdateException;

    /**
     * 基于用户id查询全部收货地址
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);

    /**
     * 为用户添加一条收货地址
     * @param uid
     * @param username
     * @param address
     * @throws AddressCountLimitException
     * @throws InsertException
     */
    void createAddress(Integer uid,String username, Address address)
            throws AddressCountLimitException, InsertException;
    /**
     * 根据Aid修改收货地址
     * @param address
     */
    void updateAddressByAid(Address address, UserEntity user)
            throws AddressNotFoundException;

}
