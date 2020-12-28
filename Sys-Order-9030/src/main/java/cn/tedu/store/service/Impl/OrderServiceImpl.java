package cn.tedu.store.service.Impl;

import cn.tedu.store.entity.*;
import cn.tedu.store.ex.*;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired(required = false)
    OrderMapper mapper;
    @Autowired
    IUserService userService;
    @Autowired
    ICartService cartService;
    @Autowired
    IProductService productService;
    @Autowired
    IAddressService addressService;

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(3);
    // 订单限时支付时间
    private static final Integer ORDER_TIMEOUT_MINUTES=15;

    /**
     * 启动一个一次性延迟任务的方法
     * @param oid
     */
    public void delayCloseOrder(Integer oid, String username) {
        System.err.println("计时器启动："+new Date()+":"+oid);
        Runnable runner = new MyRunner(oid, username);
        ScheduledFuture<?> handle =
                scheduler.schedule(runner, ORDER_TIMEOUT_MINUTES, TimeUnit.MINUTES);
    }


    @Override
    public void closeOrder(Integer oid,String username)
            throws RecordNotFoundException, UpdateException {
        System.err.println("closeOrder:"+oid+":"+new Date());
        // 基于oid查询订单状态
        Order order=mapper.getById(oid);
        if(order==null){
            throw new RecordNotFoundException("关闭订单异常：订单记录不存在");
        }
        if(order.getStatus().equals(Order.ORDER_STATUS_UNPAID)){
            // 如果是未支付-> 修改状态为超时 2
            changeStatus(oid, Order.ORDER_STATUS_TIMEOUT,username);
            // 归还订单的库存
            List<OrderItem> orderItems=order.getOrderItems();
            for(OrderItem item:orderItems){
                productService.increaseProductNum(item.getPid(),item.getNum());
            }
        }
    }

    @Override
    public void changeStatus(Integer oid, Integer status, String username)
            throws UpdateException {
        // 参数验证
        Integer row=mapper.updateStatus(oid,status,username,new Date());
        if(row!=1){
            throw new UpdateException("更新订单状态异常：数据更新失败");
        }
    }
    @Override
    public void finishOrder(Integer uid,Integer oid,String username)throws UpdateException,RecordNotFoundException{
        Order order=mapper.getById(oid);
        if(order==null){
            throw new QueryException("结束订单异常：订单不存在");
        }
        if(!order.getUid().equals(uid)){
            System.out.println(uid+","+order.getUid());
            throw new RecordNotFoundException("完成订单异常：用户没有该订单");
        }
        changeStatus(oid,Order.ORDER_STATUS_PAID,username);
        Integer row=mapper.updatePayTime(oid,username,new Date());
        if(row!=1){
            throw new UpdateException("更新订单支付时间异常：数据更新失败");
        }
    }
    @Override
    public List<Order> findByUid(Integer uid) {
        return mapper.listByUid(uid);
    }

    @Override
    @Transactional
    public void createOrder(Integer aid, Integer[] cids, Integer uid, String username)
        throws RecordNotFoundException, ProductOutOfStockException, UpdateException,
            InsertException, DeleteException {
        // 创建当前时间对象
        Date now=new Date();
        // 参数验证 TODO
        // 逻辑外键：
        // 验证uid和cid存在
        UserEntity user=userService.queryByUid(uid);
        if(user==null){
            throw new RecordNotFoundException("添加订单异常：用户信息不存在");
        }
        // 基于cids查询购物车记录
        List<CartVO> cartList=cartService.findByCids(cids, uid);
        // 判断返回值长度是否与cids长度不一致
        if(cartList.size()!=cids.length){
            // 是：RecordNotFoundException
            throw new RecordNotFoundException("添加订单异常：购物车项不存在");
        }
        // 验证aid
        // 基于aid查询收货地址记录
        Address address=addressService.findByAid(aid);
        // 判断返回值是否为null
        if(address==null){
            // 是：RecordNotFoundException
            throw new RecordNotFoundException("添加订单异常：收货地址不存在");
        }

        // 销库存
        // 遍历查询到的购物车记录
        for(CartVO cartVO:cartList){
            // 调用业务层方法，减少对应商品的库存
            productService.reduceProductNum(cartVO.getPid(),cartVO.getNum());
        }

        // 添加订单记录
        // 创建订单对象
        Order order=new Order();
        // 向订单对象中添加uid
        order.setUid(uid);
        // 向订单对象中添加收货地址相关信息
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        // 基于购物车记录计算订单总价格
        long totalPrice=0L;
        // 遍历查询到的购物车记录
        for(CartVO cartVO:cartList){
            totalPrice+=cartVO.getRealPrice()*cartVO.getNum();
        }
        // 向订单对象中添加订单总价格
        order.setPrice(totalPrice);
        // 设置订单状态为未支付
        order.setStatus(Order.ORDER_STATUS_UNPAID);
        // 向订单对象中添加日志信息
        order.setOrderTime(now);
        order.setCreatedTime(now);
        order.setCreatedUser(username);
        order.setModifiedTime(now);
        order.setModifiedUser(username);
        // 调用持久层方法添加订单记录
        Integer row=mapper.insertOrder(order);
        // 判断返回值结果是否不为1 或 订单对象中的id是否为null
        if(row!=1 || order.getId()==null){
            // 是：InsertException
            throw new InsertException("添加订单异常：订单添加失败");
        }

        // 添加订单项记录
        // 遍历查询到的购物车记录
        for(CartVO cartVO:cartList){
            // 创建订单项对象
            OrderItem item=new OrderItem();
            // 向订单项对象中添加商品信息
            item.setPid(cartVO.getPid());
            item.setNum(cartVO.getNum());
            item.setImage(cartVO.getImage());
            item.setTitle(cartVO.getTitle());
            item.setPrice(cartVO.getRealPrice());
            // 向订单项对象中添加订单id
            item.setOid(order.getId());
            // 向订单项对象中添加日志信息
            item.setCreatedTime(now);
            item.setCreatedUser(username);
            item.setModifiedTime(now);
            item.setModifiedUser(username);
            // 调用持久层方法，添加一条订单项记录
            Integer row2=mapper.insertOrderItem(item);
            // 判断返回值是否不为1
            if(row2!=1){
                // 是：InsertException
                throw new InsertException("添加订单异常：订单项添加失败");
            }
        }

        // 基于cids删除购物车记录
        cartService.removeCartList(cids);
        // 订单的限时支付
        delayCloseOrder(order.getId(),username);
    }

    /**
     * 自定义的Runnable接口实现类
     * 用于封装一次性计时器到期时执行的逻辑
     * 在这里是调用closeOrder方法，关闭订单
     */
    class MyRunner implements Runnable{

        private Integer oid;
        private String username;

        public MyRunner(Integer oid,String username){
            this.oid=oid;
            this.username=username;
        }

        @Override
        public void run() {
            closeOrder(this.oid,this.username);
        }
    };

}













