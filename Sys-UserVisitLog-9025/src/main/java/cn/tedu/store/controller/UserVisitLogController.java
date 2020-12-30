package cn.tedu.store.controller;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.UserEntity;
import cn.tedu.store.entity.UserVisitLog;
import cn.tedu.store.service.IUserVisitLogService;
import cn.tedu.store.util.JsonResult;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/userVisitLog")
public class UserVisitLogController {

    @Autowired(required = false)
    IUserVisitLogService service;

    @RequestMapping("/save")
    public void saveVisitLog(UserVisitLog log) {
        service.saveVisitLog(log);
    }

    @RequestMapping("/getCidByUid")
    public Integer getCidByUid(Integer uid) {
        return service.getCidByUid(uid);
    }

    @RequestMapping("/listVisited")
    public JsonResult<List<Product>> listVisited(HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        return JsonResult.getSuccessJR(service.getProductsVisitedList(user.getUid()));
    }
}
