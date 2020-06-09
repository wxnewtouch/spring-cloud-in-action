package com.wally.loggerservice.rabbit;

import com.alibaba.fastjson.JSON;
import com.wally.loggerservice.entity.SysLog;
import com.wally.loggerservice.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private SysLogService sysLogService;

    public void receiveMessage(String message) {
        log.info("Received: {}",message);
        SysLog sysLog=  JSON.parseObject(message,SysLog.class);
        sysLogService.saveLogger(sysLog);
        latch.countDown();
    }

}
