package cn.tedu.store.service;

import cn.tedu.store.entity.CartVO;
import cn.tedu.store.ex.DeleteException;
import cn.tedu.store.ex.RecordNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CART")
public interface ICartService {

    @GetMapping("/carts/findByCidsFromOrder")
    List<CartVO> findByCids(@RequestParam("cids") Integer[] cids,@RequestParam("uid") Integer uid);

    @PostMapping("/carts/removeList")
    void removeCartList(@RequestParam("cids") Integer[] cids) throws RecordNotFoundException, DeleteException;
}
