package com.example.HystrixWithMDCDemo.aspect;

import com.example.HystrixWithMDCDemo.config.HystrixExecutionConfiguration;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@RequiredArgsConstructor
public class TransferHystrixThreadContextAspect {

    private final HystrixExecutionConfiguration hystrixExecutionConfiguration;

    @After("@annotation(com.example.HystrixWithMDCDemo.aspect.TransferHystrixThreadContext)")
    public void transferContext(JoinPoint joinPoint) {
        MDC.setContextMap(hystrixExecutionConfiguration.getSharedContextMap());
        hystrixExecutionConfiguration.clearSharedContext();
    }
}
