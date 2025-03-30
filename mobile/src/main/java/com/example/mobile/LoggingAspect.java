package com.example.mobile;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log method entry
    @Before("execution(* com.example.mobile.controller.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Entering method: {} with arguments: {}", methodName, args);
    }

    // Log method exit (successful execution)
    @AfterReturning(pointcut = "execution(* com.example.mobile.controller.*.*(..))", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Exiting method: {} with return value: {}", methodName, result);
    }

    // Log method exit (exception thrown)
    @AfterThrowing(pointcut = "execution(* com.example.mobile.controller.*.*(..))", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception in method: {} with message: {}", methodName, exception.getMessage());
    }
}