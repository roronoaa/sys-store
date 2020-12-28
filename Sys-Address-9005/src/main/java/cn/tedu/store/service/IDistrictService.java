package cn.tedu.store.service;

import cn.tedu.store.entity.District;
import cn.tedu.store.ex.EmptyArgumentException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "DISTRICT")
public interface IDistrictService {

    /**
     * 基于编号查询名称
     * @param code
     * @return
     */
    @RequestMapping("/districts/findNameByCode")
    String findNameByCode(@RequestParam("code") String code)
            throws EmptyArgumentException;

}

