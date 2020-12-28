package cn.tedu.store.entity;

import java.util.ArrayList;
import java.util.List;

public class Category {
    Integer id;
    Integer isSort;
    Integer lev;
    String name;
    Integer pid;
    Double rate;
    String tags;
    String thumb;
    List<Category> children;

    public Category(Integer id, Integer isSort, Integer lev, String name, Integer pid, Double rate, String tags, String thumb) {
        this.id = id;
        this.isSort = isSort;
        this.lev = lev;
        this.name = name;
        this.pid = pid;
        this.rate = rate;
        this.tags = tags;
        this.thumb = thumb;
        children=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public Integer getPid() {
        return pid;
    }
    public void addMyCategory(Category myCategory){
        children.add(myCategory);
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsSort() {
        return isSort;
    }

    public void setIsSort(Integer isSort) {
        this.isSort = isSort;
    }

    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}