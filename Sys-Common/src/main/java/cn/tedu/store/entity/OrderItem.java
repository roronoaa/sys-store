package cn.tedu.store.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 封装一个订单项的实体类
 */
public class OrderItem extends BaseEntity{

    private Integer id;
    private Integer oid;
    private Integer pid;
    private Integer num;
    private Long price;
    private String image;
    private String title;

    public OrderItem() {
    }

    public OrderItem(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer id, Integer oid, Integer pid, Integer num, Long price, String image, String title) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.id = id;
        this.oid = oid;
        this.pid = pid;
        this.num = num;
        this.price = price;
        this.image = image;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) &&
                Objects.equals(oid, orderItem.oid) &&
                Objects.equals(pid, orderItem.pid) &&
                Objects.equals(num, orderItem.num) &&
                Objects.equals(price, orderItem.price) &&
                Objects.equals(image, orderItem.image) &&
                Objects.equals(title, orderItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, oid, pid, num, price, image, title);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", num=" + num +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                "} " + super.toString();
    }
}
