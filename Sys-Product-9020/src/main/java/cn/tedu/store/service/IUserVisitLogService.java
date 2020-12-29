package cn.tedu.store.service;

import cn.tedu.store.entity.UserVisitLog;
import cn.tedu.store.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service("USERLOG")
@FeignClient(name = "USERVISITLOG", fallbackFactory = IUserVisitLogServiceHystrix.class)
public interface IUserVisitLogService {

    @RequestMapping("/userVisitLog/save")
    JsonResult<Void> saveVisitLog(@RequestBody UserVisitLog log);

    @RequestMapping("/userVisitLog/getCidByUid")
    JsonResult<Integer> getCidByUid(@RequestParam("uid") Integer uid);
}
