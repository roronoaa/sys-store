package cn.tedu.store.mapper;

import cn.tedu.store.entity.District;

import java.util.List;

/**
 * 省市区信息的持久层接口
 */
public interface DistrictMapper {

    /**
     * 基于编码查询名称
     * @param code
     * @return
     */
    String getNameByCode(String code);

    /**
     * 基于父级编号查询所有子级信息
     * @param parent
     * @return
     */
    List<District> listByParent(String parent);

    List<District> listAllDistricts();
}
