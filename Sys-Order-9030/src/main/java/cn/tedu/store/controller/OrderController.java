package cn.tedu.store.controller;

import cn.tedu.store.entity.Bill;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    IOrderService service;

    @RequestMapping("/create")
    public JsonResult<Integer> createOrder(Integer aid, Integer[] cids, HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");

        return JsonResult.getSuccessJR(service.createOrder(aid,cids,user.getUid(),user.getUsername()));
    }

    @RequestMapping("/list")
    public JsonResult<List<Order>> findOrder(HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        List<Order> data=service.findByUid(user.getUid());
        return JsonResult.getSuccessJR(data);
    }
    @RequestMapping("/finish")
    public JsonResult<Void> finishedOrder(HttpSession session,Integer oid){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.finishOrder(user.getUid(),oid,user.getUsername());
        return JsonResult.getSuccessJR();
    }
    @GetMapping("/selectBillByUid")
    public JsonResult<List<Bill>> selectBillByUid(HttpSession session){
        UserEntity user = (UserEntity) session.getAttribute("user");
        return JsonResult.getSuccessJR(service.selectBillByUid(user.getUid()));
    }
    @GetMapping("/getOrder")
    public JsonResult<Order> getOrder(HttpSession session,Integer oid){
        return JsonResult.getSuccessJR(service.findByOid(oid));
    }
    @PostMapping("/updateStatusByOid")
    public JsonResult<Void> cancelOrderByOid(Integer oid,Integer status,HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        service.changeStatus(oid,status,user.getUsername());
        return JsonResult.getSuccessJR();
    }

}
