package cn.tedu.store.controller;

import cn.tedu.store.entity.UserVisitLog;
import cn.tedu.store.service.IUserVisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
