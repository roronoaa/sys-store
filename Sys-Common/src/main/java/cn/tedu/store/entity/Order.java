package cn.tedu.store.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 封装订单数据的实体类
 */
public class Order extends BaseEntity{

    public static final Integer ORDER_STATUS_UNPAID=0;
    public static final Integer ORDER_STATUS_PAID=1;
    public static final Integer ORDER_STATUS_TIMEOUT=2;

    private Integer id;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Integer status; // '订单状态，0-未支付 1-已支付'
    private Long price; // '订单总价'
    private Date orderTime; // '订单创建时间'
    private Date payTime; // '订单支付时间'
    private List<OrderItem> orderItems; // 订单关联的订单项

    public Order() {
    }

    public Order(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer id, Integer uid, String recvName, String recvPhone, String recvProvince, String recvCity, String recvArea, String recvAddress, Integer status, Long price, Date orderTime, Date payTime) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.id = id;
        this.uid = uid;
        this.recvName = recvName;
        this.recvPhone = recvPhone;
        this.recvProvince = recvProvince;
        this.recvCity = recvCity;
        this.recvArea = recvArea;
        this.recvAddress = recvAddress;
        this.status = status;
        this.price = price;
        this.orderTime = orderTime;
        this.payTime = payTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvPhone() {
        return recvPhone;
    }

    public void setRecvPhone(String recvPhone) {
        this.recvPhone = recvPhone;
    }

    public String getRecvProvince() {
        return recvProvince;
    }

    public void setRecvProvince(String recvProvince) {
        this.recvProvince = recvProvince;
    }

    public String getRecvCity() {
        return recvCity;
    }

    public void setRecvCity(String recvCity) {
        this.recvCity = recvCity;
    }

    public String getRecvArea() {
        return recvArea;
    }

    public void setRecvArea(String recvArea) {
        this.recvArea = recvArea;
    }

    public String getRecvAddress() {
        return recvAddress;
    }

    public void setRecvAddress(String recvAddress) {
        this.recvAddress = recvAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(uid, order.uid) &&
                Objects.equals(recvName, order.recvName) &&
                Objects.equals(recvPhone, order.recvPhone) &&
                Objects.equals(recvProvince, order.recvProvince) &&
                Objects.equals(recvCity, order.recvCity) &&
                Objects.equals(recvArea, order.recvArea) &&
                Objects.equals(recvAddress, order.recvAddress) &&
                Objects.equals(status, order.status) &&
                Objects.equals(price, order.price) &&
                Objects.equals(orderTime, order.orderTime) &&
                Objects.equals(payTime, order.payTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, uid, recvName, recvPhone, recvProvince, recvCity, recvArea, recvAddress, status, price, orderTime, payTime);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", recvName='" + recvName + '\'' +
                ", recvPhone='" + recvPhone + '\'' +
                ", recvProvince='" + recvProvince + '\'' +
                ", recvCity='" + recvCity + '\'' +
                ", recvArea='" + recvArea + '\'' +
                ", recvAddress='" + recvAddress + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                ", orderItems=" + orderItems +
                "} " + super.toString();
    }
}
