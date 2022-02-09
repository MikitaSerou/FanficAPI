package com.example.fanficapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.example.fanficapi.service..*(..)))")
    public Object profileServiceMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info("Execute " + className + "." + methodName + "(" + Arrays.toString(methodSignature.getParameterTypes()) +
                Arrays.toString(methodSignature.getParameterNames()) + ")" +
                ", return: " + methodSignature.getReturnType().getSimpleName() + " - " +
                stopWatch.getTotalTimeMillis() + " ms");
        return result;
    }
}
