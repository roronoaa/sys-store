package cn.tedu.store.controller;

import cn.tedu.store.entity.CartVO;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    ICartService service;

    @GetMapping("/findByCids")
    public JsonResult<List<CartVO>> findByCids(Integer[] cids, HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        List<CartVO> data=service.findByCids(cids, user.getUid());
        return JsonResult.getSuccessJR(data);
    }

    @PostMapping("/changeNum")
    public JsonResult<Void> changeNum(Integer cid, Integer num, HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.changeNum(cid,num,user.getUid(),user.getUsername());
        return JsonResult.getSuccessJR();
    }

    @PostMapping("/removeList")
    public JsonResult<Void> removeCartList(Integer[] cids){
        service.removeCartList(cids);
        return JsonResult.getSuccessJR();
    }

    @PostMapping("/remove")
    public JsonResult<Void> removeCart(Integer cid, HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.removeCart(cid,user.getUid());
        return JsonResult.getSuccessJR();
    }

    @GetMapping("/list")
    public JsonResult<List<CartVO>> getCartList(HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        List<CartVO> list=service.findCartList(user.getUid());
        return JsonResult.getSuccessJR(list);
    }

    @PostMapping("/create")
    public JsonResult<Void> createCart(Integer pid, Integer num,
                                       HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.createCart(pid,num,user.getUid(),user.getUsername());
        return JsonResult.getSuccessJR();
    }


    @GetMapping("/findByCidsFromOrder")
    public List<CartVO> findByCids(Integer[] cids, Integer uid){
        List<CartVO> data=service.findByCids(cids, uid);
        return data;
    }
    @GetMapping("/setCheck")
    public JsonResult<Integer> setCheck(HttpSession session,Integer[] cids,Integer check){
        UserEntity user=(UserEntity)session.getAttribute("user");
        return JsonResult.getSuccessJR(service.setCheck(user.getUid(),cids,check));
    }
}
