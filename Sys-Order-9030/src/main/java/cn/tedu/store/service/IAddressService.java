package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.ex.RecordNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ADDRESS")
public interface IAddressService {

    @GetMapping("/addresses/findByAid")
    Address findByAid(@RequestParam("aid") Integer aid)
            throws RecordNotFoundException;
}
