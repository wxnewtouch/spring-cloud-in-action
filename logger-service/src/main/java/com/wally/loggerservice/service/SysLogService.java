package com.wally.loggerservice.service;

import com.wally.loggerservice.dao.SysLogDao;
import com.wally.loggerservice.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    public void saveLogger(SysLog sysLog){
        sysLogDao.save(sysLog);
    }
}
