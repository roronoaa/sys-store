package cn.tedu.store.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 封装购物车信息的实体类
 */
public class Cart extends BaseEntity {

    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private Integer check;
    public Cart() {
    }

    public Cart(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer cid, Integer uid, Integer pid, Long price, Integer num, Integer check) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.cid = cid;
        this.uid = uid;
        this.pid = pid;
        this.price = price;
        this.num = num;
        this.check = check;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cid, cart.cid) &&
                Objects.equals(uid, cart.uid) &&
                Objects.equals(pid, cart.pid) &&
                Objects.equals(price, cart.price) &&
                Objects.equals(num, cart.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cid, uid, pid, price, num);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pic=" + pid +
                ", price=" + price +
                ", num=" + num +
                "} " + super.toString();
    }
}
