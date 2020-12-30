package cn.tedu.store.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 保存商品种类信息的实体类
 */
public class ProductCategory extends  BaseEntity{

    private Long id;
    private Integer parentId;
    private String name;
    private Integer status; // 状态 1：正常   0：删除
    private Integer sortOrder;
    private Integer isParent; // 是否是父分类 1：是  0：否

    public ProductCategory() {
    }

    public ProductCategory(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Long id, Integer parentId, String name, Integer status, Integer sortOrder, Integer isParent) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.isParent = isParent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(status, that.status) &&
                Objects.equals(sortOrder, that.sortOrder) &&
                Objects.equals(isParent, that.isParent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, parentId, name, status, sortOrder, isParent);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sortOrder=" + sortOrder +
                ", isParent=" + isParent +
                "} " + super.toString();
    }
}
