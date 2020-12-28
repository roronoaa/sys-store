package cn.tedu.store.service;

import cn.tedu.store.entity.District;
import cn.tedu.store.entity.MyDistrict;
import cn.tedu.store.ex.EmptyArgumentException;

import java.util.List;


public interface IDistrictService {

    /**
     * 基于编号查询名称
     * @param code
     * @return
     */
    String findNameByCode(String code)
        throws EmptyArgumentException;

    /**
     * 基于父级编号查询子级信息
     * @param parent
     * @return
     */
    List<District> findByParent(String parent);

    List<MyDistrict> listAllDistricts();
}
