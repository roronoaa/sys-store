package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 订单模块的持久层接口
 */
public interface OrderMapper {

    /**
     * 基于用户id查询该用户所有订单记录
     * 关联查询所有订单项记录
     * @param uid
     * @return
     */
    List<Order> listByUid(Integer uid);

    /**
     * 基于订单id修改订单状态
     * @param id
     * @param status
     * @param username
     * @param modifiedTime
     * @return
     */
    Integer updateStatus(
            @Param("id") Integer id,
            @Param("status") Integer status,
            @Param("username") String username,
            @Param("modifiedTime") Date modifiedTime);

    Integer updatePayTime(@Param("oid") Integer oid,@Param("username") String username,@Param("payTime") Date payTime);

    /**
     * 基于订单id查询订单数据
     * 关联查询所有订单项记录
     * @param id
     * @return
     */
    Order getById(Integer id);

    /**
     * 添加一条订单记录
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 添加一条订单项记录
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);
}
