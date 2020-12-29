package cn.tedu.store.service;

import cn.tedu.store.entity.CartVO;
import cn.tedu.store.ex.*;

import java.util.List;

/**
 * 购物车模块的业务层接口
 */
public interface ICartService {

    /**
     * 基于购物车id数组查询购物车记录
     * @param cids 购物车id的数组
     * @param uid 用户id
     * @return
     */
    List<CartVO> findByCids(Integer[] cids, Integer uid);

    /**
     * 基于购物车id修改购物车商品数量
     * @param cid 购物车记录id
     * @param num 增加或减少的商品数量, 增加传入正值，减少传入负值
     * @param uid 用户id
     * @param username 用户名
     * @throws RecordNotFoundException
     * @throws AccessDeniedException
     * @throws UpdateException
     */
    void changeNum(Integer cid,Integer num,Integer uid,String username)
            throws RecordNotFoundException, AccessDeniedException, UpdateException;

    /**
     * 基于购物车id数组批量删除购物车记录
     * @param cids
     */
    void removeCartList(Integer[] cids) throws RecordNotFoundException,DeleteException;

    /**
     * 基于购物车id删除购物车记录
     * @param cid
     * @param uid
     */
    void removeCart(Integer cid,Integer uid)
            throws AccessDeniedException, RecordNotFoundException, DeleteException;

    /**
     * 基于用户id查询所有购物车记录
     * @param uid
     * @return
     */
    List<CartVO> findCartList(Integer uid)
        throws RecordNotFoundException;

    /**
     * 添加一条购物车记录
     * @param pid 商品id
     * @param num 商品数量
     * @param uid 用户id
     * @param username 用户名
     * @throws RecordNotFoundException
     * @throws UpdateException
     * @throws InsertException
     */
    void createCart(Integer pid, Integer num,Integer uid,String username)
            throws RecordNotFoundException, UpdateException, InsertException;
    Integer setCheck(Integer uid,Integer[] cids,Integer check);
}
