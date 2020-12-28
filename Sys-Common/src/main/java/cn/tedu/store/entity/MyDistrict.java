package cn.tedu.store.entity;

import java.util.ArrayList;
import java.util.List;

public class MyDistrict {
    Integer id;
    String parent;
    String code;
    String name;
    List<MyDistrict> children;

    public MyDistrict(Integer id, String parent, String code, String name) {
        this.id = id;
        this.parent = parent;
        this.code = code;
        this.name = name;
        //children=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MyDistrict> getChildren() {
        return children;
    }

    public void setChildren(List<MyDistrict> children) {
        this.children = children;
    }
    public void init() {
         children=new ArrayList<>();
    }
    public void addChildren(MyDistrict myDistrict){
        children.add(myDistrict);
    }
    public boolean isEmpty(){
        return children==null;
    }
}
