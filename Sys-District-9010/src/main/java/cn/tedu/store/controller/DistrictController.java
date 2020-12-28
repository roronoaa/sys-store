package cn.tedu.store.controller;

import cn.tedu.store.entity.District;
import cn.tedu.store.entity.MyDistrict;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController {
    @Autowired
    IDistrictService service;

    @RequestMapping("/")
    public JsonResult<List<District>> findDistrict(String parent) {
        List<District> list = service.findByParent(parent);
        return JsonResult.getSuccessJR(list);
    }

    @RequestMapping("/findNameByCode")
    public String findNameByCode(String code) {
        String name = service.findNameByCode(code);
        return name;
    }
    @GetMapping("/findAllDistricts")
    public JsonResult<List<MyDistrict>> findAllDistricts(){
        return JsonResult.getSuccessJR(service.listAllDistricts());
    }
}
