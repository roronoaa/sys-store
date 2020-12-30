package cn.tedu.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 封装一条用户访问商品详情的记录的实体类
 */
public class UserVisitLog implements Serializable {

    private Integer id;
    private Integer uid; //用户id
    private Long pid; //商品id
    private Integer cid; //商品种类id
    private Date createdTime; //数据创建时间

    public UserVisitLog() {
    }

    public UserVisitLog(Integer id, Integer uid, Long pid, Integer cid, Date createdTime) {
        this.id = id;
        this.uid = uid;
        this.pid = pid;
        this.cid = cid;
        this.createdTime = createdTime;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVisitLog that = (UserVisitLog) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(cid, that.cid) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, pid, cid, createdTime);
    }

    @Override
    public String toString() {
        return "UserVisitLog{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                ", cid=" + cid +
                ", createdTime=" + createdTime +
                '}';
    }
}
