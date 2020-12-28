package cn.tedu.store.service;

import cn.tedu.store.entity.UserVisitLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USERVISITLOG")
public interface IUserVisitLogService {

    @RequestMapping("/userVisitLog/save")
    void saveVisitLog(@RequestBody UserVisitLog log);

    @RequestMapping("/userVisitLog/getCidByUid")
    Integer getCidByUid(@RequestParam("uid") Integer uid);
}
