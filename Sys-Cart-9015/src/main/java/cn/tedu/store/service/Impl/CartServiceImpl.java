package cn.tedu.store.service.Impl;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.CartVO;
import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.ex.*;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired(required = false)
    CartMapper mapper;

    @Autowired
    IUserService userService;

    @Autowired
    IProductService productService;

    @Override
    public List<CartVO> findByCids(Integer[] cids, Integer uid) {
        // 参数判断
        if(cids==null) {
            return new ArrayList<CartVO>();
        }
        List<CartVO> result=mapper.listByCids(cids);
        Iterator<CartVO> it=result.iterator();
        while(it.hasNext()){
            CartVO vo=it.next();
            // 如果购物车记录的uid和当前用户uid不一致
            if(!vo.getUid().equals(uid)){
                // 从返回结果中移除该记录
                it.remove();
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void changeNum(Integer cid, Integer num, Integer uid, String username) throws RecordNotFoundException, AccessDeniedException, UpdateException {
        // 参数验证 TODO
        // 基于cid查询购物车记录
        Cart cart=mapper.getByCidForUpdate(cid);
        // 判断返回值是否为null
        if(cart==null){
            // 是：RecordNotFoundException
            throw new RecordNotFoundException("更新数量异常：该记录不存在");
        }

        // 判断查询到的uid和传入的uid是否不一致
        // Integer 在 -128 到 127 范围内可用 == 判断
        if(!uid.equals(cart.getUid())){
            // 是：AccessDeniedException
            throw new AccessDeniedException("更新数量异常：权限不足");
        }

        // 基于旧的商品数量和传入的数量计算新的商品数量
        int newNum=cart.getNum()+num;
        // 判断新的商品数量是否小于1
        if(newNum<1){
            // 是：UpdateException
            throw new UpdateException("更新数量异常：商品数量异常");
        }

        // 调用持久层方法执行更新操作
        Integer row=mapper.updateNum(cid,newNum,username,new Date());
        // 判断返回值是否不为1
        if(row!=1){
            // 是：UpdateException
            throw new UpdateException("更新数量异常：数量更新失败");
        }
    }

    @Override
    @Transactional
    public void removeCartList(Integer[] cids)
            throws RecordNotFoundException, DeleteException {
        // 基于cids查询实际存在的记录条数
        int count=mapper.getCount(cids);
        // 判断返回值与cids的长度是否一致
        if(count!=cids.length){
            // 否：RecordNotFoundException
            throw new RecordNotFoundException("批量删除购物车异常：拟删除的数据可能不存在");
        }
        // 调用持久层方法，批量删除购物车记录
        Integer row=mapper.deleteCartList(cids);
        // 判断返回值与cids的长度是否一致
        if(!row.equals(cids.length)){
            // 否：DeleteException
            throw new DeleteException("批量删除购物车异常：数据删除失败");
        }
    }

    @Override
    public void removeCart(Integer cid, Integer uid)
            throws AccessDeniedException, RecordNotFoundException, DeleteException {
        // 基于cid查询购物车记录
        Cart cart=mapper.getByCid(cid);
        // 判断返回值是否为null
        if(cart==null){
            // 是：RecordNotFoundException
            throw new RecordNotFoundException("删除购物车异常：记录不存在");
        }
        // 判断查询结果中的uid与传入的uid是否不一致
        if(!uid.equals(cart.getUid())){
            // 是：AccessDeniedException
            throw new AccessDeniedException("删除购物车异常：权限不足");
        }
        // 调用持久层方法执行删除操作
        Integer row=mapper.deleteCartByCid(cid);
        // 判断返回值结果是否不为1
        if(row!=1){
            // 是：DeleteException
            throw new DeleteException("删除购物车异常：记录删除失败");
        }
    }

    @Override
    public List<CartVO> findCartList(Integer uid) throws RecordNotFoundException {
        List<CartVO> list=mapper.listCart(uid);
        if(list==null || list.size()==0){
            throw new RecordNotFoundException("查询购物车列表异常：未查到记录");
        }
        return list;
    }

    @Override
    public void createCart(Integer pid, Integer num, Integer uid, String username)
            throws RecordNotFoundException, UpdateException, InsertException {
        // 非空验证 TODO
        // 验证uid对应的用户数据是否存在
        UserEntity user=userService.queryByUid(uid);
        if(user==null || user.getIsDelete()==1){
            throw new RecordNotFoundException("添加购物车异常：用户信息不存在");
        }
        // 基于商品id查询商品数据
        // 判断结果是否为null
        // 是：RecordNotFoundException
        // 判断商品的status是否不为1
        // 是：RecordNotFoundException -> 已下架
        Product product=productService.findById(pid);
        if(product==null || product.getStatus()!=1){
            throw new RecordNotFoundException("添加购物车异常：商品不存在或已下架");
        }

        // 基于uid和pid查询购物车记录
        Cart cart=mapper.getByUidAndPid(uid,pid);
        // 判断查询结果是否为null
        if(cart==null){  // 是：添加新购物车记录
            // 创建购物车记录，封装必要的属性，包括日志字段
            cart=new Cart(username,null,username,null,
                    null,uid,pid,product.getPrice(),num);
            // 调用持久层方法，添加购物车记录
            Integer row=mapper.insertCart(cart);
            // 判断返回值是否不为1
            if(row!=1){
                // 是：InsertException
                throw new InsertException("添加购物车异常：新记录添加失败");
            }
        }else{// 否：更新已有购物车记录
            // 计算新的商品数量
            int newNum=cart.getNum()+num;
            // 调用持久层方法，更新购物车记录
            Integer row=mapper.updateNum(cart.getCid(),newNum,username,new Date());
            // 判断返回值是否不为1
            if(row!=1){
                // 是：UpdateException
                throw new UpdateException("添加购物车异常：记录更新失败");
            }
        }
    }

}
