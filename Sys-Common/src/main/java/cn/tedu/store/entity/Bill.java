package cn.tedu.store.entity;

import java.util.Date;
import java.util.Objects;

public class Bill extends BaseEntity{
    private Integer uid;
    private Integer oid;
    private Integer pid;
    private String pname;
    private String ptype;
    private Integer price;

    public Bill() {
    }


    public Bill(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer uid, Integer oid, Integer pid, String pname, String ptype, Integer price) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.uid = uid;
        this.oid = oid;
        this.pid = pid;
        this.pname = pname;
        this.ptype = ptype;
        this.price = price;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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



    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bill bill = (Bill) o;
        return Objects.equals(uid, bill.uid) &&
                Objects.equals(oid, bill.oid) &&
                Objects.equals(pid, bill.pid) &&
                Objects.equals(pname, bill.pname) &&
                Objects.equals(ptype, bill.ptype) &&
                Objects.equals(price, bill.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uid, oid, pid, pname, ptype, price);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "uid=" + uid +
                ", oid=" + oid +
                ", pid=" + pid +
                ", pname=" + pname +
                ", ptype=" + ptype +
                ", price=" + price +
                '}';
    }
}
