package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import cn.tedu.store.ex.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 订单模块的业务层接口
 */
public interface IOrderService {

    /**
     * 基于用户id查询订单记录
     * @param uid
     * @return
     */
    List<Order> findByUid(@RequestParam("uid") Integer uid);

    /**
     * 添加一条订单记录
     * 生成多条关联的订单项记录
     * @param aid
     * @param cids
     * @param uid
     * @param username
     */
    Integer createOrder(@RequestParam("aid") Integer aid, @RequestParam("cids") Integer[] cids,@RequestParam("uid") Integer uid, @RequestParam("username") String username);

    /**
     * 基于订单id关闭订单支付窗口
     * @param oid
     * @param username
     */
    void closeOrder(@RequestParam("oid") Integer oid, @RequestParam("username") String username);

    /**
     * 基于订单id修改订单状态
     * @param oid
     * @param status 0-未支付 1-已支付 2-已超时
     * @param username
     * @throws UpdateException
     */
    void changeStatus(@RequestParam("oid") Integer oid, @RequestParam("status") Integer status, @RequestParam("username") String username)
        throws UpdateException;
    void finishOrder(@RequestParam("uid") Integer uid, @RequestParam("oid") Integer oid,@RequestParam("username") String username)throws RecordNotFoundException;
}
