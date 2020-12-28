package cn.tedu.store.service.Impl;

import cn.tedu.store.entity.Address;
import cn.tedu.store.ex.*;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired(required = false)
    AddressMapper mapper;

    @Autowired
    IDistrictService districtService;


    private static final int MAX_ADDRESS=3;

    @Override
    public Address findByAid(Integer aid) throws RecordNotFoundException {
        if(aid==null || aid==0){
            throw new RecordNotFoundException("查询收货地址异常：id不能为空");
        }
        return mapper.findByAid(aid);
    }

    @Override
    @Transactional
    public void removeAddress(Integer aid, Integer uid, String username) throws AddressNotFoundException, AccessDeniedException, DeleteException, UpdateException {
        // 非空验证 TODO
        Address address=null;
        try{
            address=mapper.findByAid(aid);
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateException("删除收货地址异常："+e.getMessage(),e);
        }
        // 验证aid对应的记录是否不存在
        if(address==null){
            // 是：AddressNotFoundException
            throw new AddressNotFoundException("删除收货地址异常：地址不存在");
        }

        // 验证当前用户是否没有该记录操作权限
        if(!uid.equals(address.getUid())){
            // 是：AccessDeniedException
            throw new AccessDeniedException("删除收货地址异常：权限不足");
        }

        // 删除aid对应的收货地址
        int row1=0;
        try{
            row1=mapper.deleteByAid(aid);
        }catch (Exception e){
            e.printStackTrace();
            throw new DeleteException("删除收货地址异常："+e.getMessage(),e);
        }
        // 判断返回值结果是否不为1
        if(row1!=1){
            // 是：DeleteException
            throw new DeleteException("删除收货地址异常：地址删除失败");
        }

        // 判断查询结果中isDefault是否为1
        if(address.getIsDefault().equals(1)){
            // 是：查找最后更新的收货地址
            Address lastModified=null;
            try{
                lastModified=mapper.findLastModified(uid);
            }catch (Exception e){
                e.printStackTrace();
                throw new UpdateException("删除收货地址异常："+e.getMessage(),e);
            }
            // 判断查询结果是否不为null
            if(lastModified!=null){
                // 是：将该地址设为默认收货地址
                int row2=0;
                try{
                    row2=mapper.updateDefault(lastModified.getAid(),username,new Date());
                }catch (Exception e){
                    e.printStackTrace();
                    throw new UpdateException("删除收货地址异常："+e.getMessage(),e);
                }
                // 判断返回值是否不为1
                if(row2!=1){
                    // 是：UpdateException
                    throw new UpdateException("删除收货地址异常：设置默认收货地址失败");
                }
            }
        }
    }

    @Override
    @Transactional
    public void setDefault(Integer aid, Integer uid, String username)
            throws AddressNotFoundException, AccessDeniedException, UpdateException {
        // 非空验证 TODO
        Address address=null;
        try{
            address=mapper.findByAid(aid);
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateException("设置默认收货地址异常："+e.getMessage(),e);
        }
        // 验证aid对应的记录是否不存在
        if(address==null){
            // 是：AddressNotFoundException
            throw new AddressNotFoundException("设置默认收货地址异常：地址不存在");
        }

        // 验证当前用户是否没有该记录操作权限
        if(!uid.equals(address.getUid())){
            // 是：AccessDeniedException
            throw new AccessDeniedException("设置默认收货地址异常：权限不足");
        }

        // 将该用户所有收货地址设为非默认
        try{
            mapper.updateNonDefault(uid);
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateException("设置默认收货地址异常："+e.getMessage(),e);
        }
        // 将指定收货地址设为默认收货地址
        int row=0;
        try{
            row=mapper.updateDefault(aid,username,new Date());
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateException("设置默认收货地址异常："+e.getMessage(),e);
        }
        // 判断返回值是否不为1
        if(row!=1){
            // 是：UpdateException
            throw new UpdateException("设置默认收货地址异常：设置失败");
        }
    }

    @Override
    public List<Address> findByUid(Integer uid) {
        List<Address> list=mapper.listByUid(uid);
        for(Address address:list){
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public void createAddress(Integer uid, String username, Address address)
            throws AddressCountLimitException, InsertException {
        // 非空验证 TODO
        // 基于用户id，查询用户地址数量
        int count=0;
        try{
            count=mapper.countByUid(uid);
        }catch (Exception e){
            e.printStackTrace();
            throw new InsertException("添加地址异常："+e.getMessage(),e);
        }
        // 判断地址数量是否达到上限
        if(count>=MAX_ADDRESS){
            // 是：AddressCountLimitException
            throw new AddressCountLimitException("添加地址异常：地址数量已达上限");
        }

        // 判断地址数量是否为0
        if(count==0){
            // 是：设置address中的isDefault为1
            address.setIsDefault(1);
        }else{
            address.setIsDefault(0);
        }

        // 补全uid
        address.setUid(uid);

        // TODO 补全省市区数据：补充省市区名称
        address.setProvinceName(districtService.findNameByCode(address.getProvinceCode()+""));
        address.setCityName(districtService.findNameByCode(address.getCityCode()+""));
        address.setAreaName(districtService.findNameByCode(address.getAreaCode()+""));

        // 添加日志信息
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        // 执行添加地址操作
        int row=0;
        try{
            row=mapper.saveAddress(address);
        }catch (Exception e){
            e.printStackTrace();
            throw new InsertException("添加地址异常："+e.getMessage(),e);
        }
        // 判断返回值是否不为1
        if(row!=1){
            // 是：InsertException
            throw new InsertException("添加地址异常：地址添加失败");
        }
    }
}
