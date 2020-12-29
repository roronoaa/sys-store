package cn.tedu.store.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 封装用户信息的实体类
 */
public class UserEntity extends BaseEntity{

    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private Integer isDelete;
    private String phone;
    private String email;
    private Integer gender; //0代表女，1代表男
    private String avatar; // 头像图片路径
    private Integer point;
    public UserEntity() {
    }

    public UserEntity(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer uid, String username, String password, String salt, Integer isDelete, String phone, String email, Integer gender, String avatar, Integer point) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.isDelete = isDelete;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
        this.point = point;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(uid, that.uid) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(salt, that.salt) &&
                Objects.equals(isDelete, that.isDelete) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uid, username, password, salt, isDelete, phone, email, gender, avatar);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", isDelete=" + isDelete +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                "} " + super.toString();
    }
}
