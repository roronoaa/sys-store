package cn.tedu.store.service;

import cn.tedu.store.ex.ProductOutOfStockException;
import cn.tedu.store.ex.RecordNotFoundException;
import cn.tedu.store.ex.UpdateException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT")
public interface IProductService {

    @PostMapping("/products/increaseProductNum")
    void increaseProductNum(@RequestParam("pid") Integer pid,@RequestParam("num") Integer num)
            throws RecordNotFoundException, UpdateException;

    @PostMapping("/products/reduceProductNum")
    void reduceProductNum(@RequestParam("pid") Integer pid,@RequestParam("num") Integer num)
            throws RecordNotFoundException, ProductOutOfStockException, UpdateException;
}
