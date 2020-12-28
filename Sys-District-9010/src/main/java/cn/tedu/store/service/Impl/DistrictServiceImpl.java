package cn.tedu.store.service.Impl;

import cn.tedu.store.entity.Category;
import cn.tedu.store.entity.District;
import cn.tedu.store.entity.MyDistrict;
import cn.tedu.store.entity.ProductCategory;
import cn.tedu.store.ex.EmptyArgumentException;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired(required = false)
    DistrictMapper mapper;

    @Override
    public String findNameByCode(String code) throws EmptyArgumentException {
        if(StringUtils.isEmpty(code)){
            throw new EmptyArgumentException("查询区域名称异常：编码为空");
        }
        return mapper.getNameByCode(code);
    }

    @Override
    public List<District> findByParent(String parent) {
        // 非空验证 TODO
        // 验证结果集是否有数据 TODO
        return mapper.listByParent(parent);
    }

    @Override
    public List<MyDistrict> listAllDistricts() {
        List<District> list=mapper.listAllDistricts();
        ArrayList<MyDistrict> mylist=new ArrayList<>();
        for(District d:list){
            if(d.getParent().equals("86")){
                mylist.add(new MyDistrict(d.getId(),d.getParent(),d.getCode(),d.getName()));
            }else{
                for(MyDistrict md:mylist){
                    if(md.getCode().equals(d.getParent())){
                        if(md.isEmpty()){
                            md.init();
                        }
                        md.addChildren(new MyDistrict(d.getId(),d.getParent(),d.getCode(),d.getName()));
                        break;
                    }
                    List<MyDistrict> temp=md.getChildren();
                    for(MyDistrict t:temp){
                        if(t.getCode().equals(d.getParent())){
                            if(t.isEmpty()){
                                t.init();
                            }
                            t.addChildren(new MyDistrict(d.getId(),d.getParent(),d.getCode(),d.getName()));
                            md.setChildren(temp);
                            break;
                        }
                    }
                }
            }
        }
        return mylist;
    }

}
