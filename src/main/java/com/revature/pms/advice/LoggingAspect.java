package com.revature.pms.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.revature.pms.services.ProductServiceImpl.*(..))")
    public void doLog() {
        logger.info("2.###I am doing the logging from aspect");
    }

    @After("execution(* com.revature.pms.services.ProductServiceImpl.*(..))")
    public void doSecurityCheck() {
        logger.info("3.###I am doing security checking");
    }

//    @Around("execution(* com.revature.pms.services.ProductServiceImpl.addProduct(..))")
//    public void doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info("1.###I am doing transaction before ");
//
//        // here you can decide whether you have to complete the method or not
//        joinPoint.proceed();
//        logger.info("###4.I am done transaction after ");
//    }
}
