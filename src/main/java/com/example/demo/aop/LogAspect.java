package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

  @Pointcut("execution(* com.example.demo.service.UserServiceImpl..*(..))")
  public void pointcut() {}

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//    Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
    Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getName());
    Object[] args = joinPoint.getArgs();
    String name = joinPoint.getSignature().getName();
    log.info(String.valueOf(args) + name);
    Object result = joinPoint.proceed();
    return result;
  }
}
