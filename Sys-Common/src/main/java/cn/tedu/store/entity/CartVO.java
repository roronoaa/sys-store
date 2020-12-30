package cn.tedu.store.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 封装购物车列表中一条记录的实体类
 */
public class CartVO implements Serializable {

    private Integer cid;
    private Integer pid;
    private String image;
    private String title;
    private Long realPrice;
    private Long price;
    private Integer num;
    private Integer uid;
    private Integer check;
    public CartVO() {
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public CartVO(Integer cid, Integer pid, String image, String title, Long realPrice, Long price, Integer num, Integer uid, Integer check) {
        this.cid = cid;
        this.pid = pid;
        this.image = image;
        this.title = title;
        this.realPrice = realPrice;
        this.price = price;
        this.num = num;
        this.uid = uid;
        this.check = check;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
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
        CartVO cartVO = (CartVO) o;
        return Objects.equals(cid, cartVO.cid) &&
                Objects.equals(pid, cartVO.pid) &&
                Objects.equals(image, cartVO.image) &&
                Objects.equals(title, cartVO.title) &&
                Objects.equals(realPrice, cartVO.realPrice) &&
                Objects.equals(price, cartVO.price) &&
                Objects.equals(num, cartVO.num) &&
                Objects.equals(uid, cartVO.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, pid, image, title, realPrice, price, num, uid);
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", realPrice=" + realPrice +
                ", price=" + price +
                ", num=" + num +
                ", uid=" + uid +
                '}';
    }
}
