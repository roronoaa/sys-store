package cn.tedu.store.service;

import cn.tedu.store.entity.UserVisitLog;
import cn.tedu.store.util.JsonResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class IUserVisitLogServiceHystrix implements FallbackFactory {


    @Override
    public IUserVisitLogService create(Throwable throwable) {
        return new IUserVisitLogService() {
            @Override
            public JsonResult<Void> saveVisitLog(UserVisitLog log) {
                return JsonResult.getFailed("服务不可用, 已降级!");
            }

            @Override
            public JsonResult<Integer> getCidByUid(Integer uid) {
                return JsonResult.getFailed("服务不可用, 已降级!");
            }
        };
    }
}
