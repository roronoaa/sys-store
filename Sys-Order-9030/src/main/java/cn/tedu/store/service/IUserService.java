package cn.tedu.store.service;


import cn.tedu.store.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER")
public interface IUserService {

    @RequestMapping("/users/queryUserById")
    UserEntity queryByUid(@RequestParam("uid") Integer uid);
}
