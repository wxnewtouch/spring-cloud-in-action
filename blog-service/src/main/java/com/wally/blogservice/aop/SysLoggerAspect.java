package com.wally.blogservice.aop;

import com.alibaba.fastjson.JSON;
import com.wally.blogservice.entity.SysLog;
import com.wally.blogservice.service.LoggerService;
import com.wally.blogservice.utils.HttpUtils;
import com.wally.blogservice.utils.UserUtils;
import com.wally.common.annotation.SysLogger;
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

@Aspect
@Component
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
        SysLogger annotation = method.getAnnotation(SysLogger.class);
        if (null != annotation) {
            sysLog.setOperation(annotation.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        Object[] args = joinPoint.getArgs();
        String params = "";
        for (Object arg : args) {
            params += JSON.toJSONString(arg);
        }
        sysLog.setParams(params);
        sysLog.setIp(HttpUtils.getIpAddress());
        String username = UserUtils.getCurrentPrinciple();
        if (!StringUtils.isEmpty(username)) {
            sysLog.setUsername(username);
        }
        sysLog.setCreateDate(new Date());
        //保存系统日志
        loggerService.log(sysLog);
    }
}
