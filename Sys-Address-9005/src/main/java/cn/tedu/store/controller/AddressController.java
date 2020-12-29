package cn.tedu.store.controller;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    IAddressService service;

    @PostMapping("/{aid}/delete")
    public JsonResult<Void> removeAddress(@PathVariable("aid") Integer aid, HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.removeAddress(aid,user.getUid(),user.getUsername());
        return JsonResult.getSuccessJR();
    }

    @RequestMapping("/{aid}/set_default")
    public JsonResult<Void> setDefault(
            @PathVariable("aid") Integer aid, HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.setDefault(aid,user.getUid(),user.getUsername());
        return JsonResult.getSuccessJR();
    }

    @RequestMapping("/list")
    public JsonResult<List<Address>> findByUid(HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        List<Address> list=service.findByUid(user.getUid());
        return JsonResult.getSuccessJR(list);
    }

    @PostMapping("/createAddress")
    public JsonResult<Void> createAddress(Address address, HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.createAddress(user.getUid(),user.getUsername(),address);
        return JsonResult.getSuccessJR();
    }

    @GetMapping("/findByAid")
    public Address findByAid(Integer aid) {
        return service.findByAid(aid);
    }

    @PostMapping("/updateAddressByAid")
    public JsonResult<Void> updateAddressByAid(Address address, HttpSession session){
        UserEntity user = (UserEntity) session.getAttribute("user");
        service.updateAddressByAid(address, user);
        return JsonResult.getSuccessJR();
    }

}
