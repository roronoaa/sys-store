package cn.tedu.store.service.Impl;

import cn.tedu.store.entity.UserVisitLog;
import cn.tedu.store.mapper.UserVisitLogMapper;
import cn.tedu.store.service.IUserVisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVisitLogImpl implements IUserVisitLogService {

    @Autowired(required = false)
    UserVisitLogMapper mapper;

    @Override
    public void saveVisitLog(UserVisitLog log) {
        mapper.insertVisitLog(log);
    }

    @Override
    public Integer getCidByUid(Integer uid) {
        return mapper.getCidByUid(uid);
    }

}
