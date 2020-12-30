package cn.tedu.store.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 保存商品信息的实体类
 */
public class Product extends BaseEntity{

    private Long id;
    private ProductCategory category;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status; // 商品状态 1：上架 2：下架 3：删除
    private Integer priority;

    public Product() {
    }

    public Product(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Long id, ProductCategory category, String itemType, String title, String sellPoint, Long price, Integer num, String image, Integer status, Integer priority) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.id = id;
        this.category = category;
        this.itemType = itemType;
        this.title = title;
        this.sellPoint = sellPoint;
        this.price = price;
        this.num = num;
        this.image = image;
        this.status = status;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(category, product.category) &&
                Objects.equals(itemType, product.itemType) &&
                Objects.equals(title, product.title) &&
                Objects.equals(sellPoint, product.sellPoint) &&
                Objects.equals(price, product.price) &&
                Objects.equals(num, product.num) &&
                Objects.equals(image, product.image) &&
                Objects.equals(status, product.status) &&
                Objects.equals(priority, product.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, category, itemType, title, sellPoint, price, num, image, status, priority);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", itemType='" + itemType + '\'' +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                "} " + super.toString();
    }
}
