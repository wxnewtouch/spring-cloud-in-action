package com.wally.blogservice.aop;

import com.wally.blogservice.service.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysLoggerAspect {
    @Autowired
    private LoggerService loggerService;

    @Pointcut("@annotation(com.wally.common.annotation.SysLogger)")
    public void loggerPointCut(){

    }

    @Before("loggerPointCut()")
    public void saveSysLog(JoinPoint joinPoint){

    }
}
