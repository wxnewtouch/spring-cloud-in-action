package com.wally.userservice.aop;

import com.alibaba.fastjson.JSON;
import com.wally.common.annotation.SysLogger;
import com.wally.userservice.entity.SysLog;
import com.wally.userservice.service.LoggerService;
import com.wally.userservice.utils.HttpUtils;
import com.wally.userservice.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Component
@Slf4j
@Aspect
public class SysLoggerAspect {

    @Autowired
    private LoggerService loggerService;

    @Pointcut("@annotation(com.wally.common.annotation.SysLogger)")
    public void loggerPointCut() {

    }

    @Before("loggerPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        if (null != sysLogger) {
            sysLog.setOperation(sysLogger.value());
        }

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        Object[] args = joinPoint.getArgs();
        String params = "";
        for (Object arg : args) {
            params += JSON.toJSONString(arg);
        }
        if (StringUtils.isNotEmpty(params)) {
            sysLog.setParams(params);
        }

        sysLog.setIp(HttpUtils.getIpAddress());
        String userName = UserUtils.getCurrentPrinciple();
        if (!StringUtils.isEmpty(userName)) {
            sysLog.setUsername(userName);
        }
        sysLog.setCreateDate(new Date());
        //保存系统日志
        loggerService.log(sysLog);
    }
}
