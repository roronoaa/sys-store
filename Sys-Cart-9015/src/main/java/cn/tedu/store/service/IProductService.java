package cn.tedu.store.service;

import cn.tedu.store.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT")
public interface IProductService {

    @RequestMapping("/products/findProductById")
    Product findById(@RequestParam("pid") Integer id);
}
