package com.example.fanficapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDateTime;

@Aspect
@Slf4j
public class LoggingAspect { //TODO Inspect this

    @Pointcut("execution(public * root.service.*.*(..))")
    public void isRepositoryLayer1() {}


    @Around("isRepositoryLayer1()")
    public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String s1 = " METOD "+signature.toString()+" TIME "+ LocalDateTime.now();
        Process logger;
        log.info(s1);
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage());
            throw e;
        }
        return result;
    }
}
