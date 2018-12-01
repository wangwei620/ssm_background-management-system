package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Controller
public class LogAop {
    //引入日志的业务类用于保存日志
    @Autowired
    private LogService logService;

    private SysLog log;

    @Autowired
    private HttpServletRequest request;

    //定义切入点
    @Pointcut("execution( * com.itheima.controller.*.*(..))")
    public void pointCut(){}


    //前置通知
    @Before("pointCut()")
    public void executeBefore(JoinPoint jp){
        //在执行方法前初始化日志对象
        log = new SysLog();
        //当前访问者的用户名
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        log.setUsername(user.getUsername());
        //当前时间为访问时间
        log.setVisitTime(new Date());
        //访问者的ip地址 借助于request获取
        log.setIp(request.getRemoteAddr());
        //访问的方法问类名+方法名 通过连接点
        Class target =jp.getTarget().getClass();
        String className = target.getSimpleName();
        String methodName=jp.getSignature().getName();
        log.setMethod(className+"====="+methodName);
    }

    //后置通知
    @AfterReturning("pointCut()")
    public void executeAfter(){
        log.setExecuteMsg("执行成功");
        log.setExecuteResult("success");
        log.setExecuteTime(new Date().getTime()-log.getVisitTime().getTime());
        logService.saveLog(log);

    }

    //异常通知
    @AfterThrowing(pointcut = "pointCut()",throwing = "e")
    public void executeException(Exception e){
        log.setExecuteMsg(e.getMessage());
        log.setExecuteResult("exception");
        log.setExecuteTime(new Date().getTime()-log.getVisitTime().getTime());
        logService.saveLog(log);
    }
}
