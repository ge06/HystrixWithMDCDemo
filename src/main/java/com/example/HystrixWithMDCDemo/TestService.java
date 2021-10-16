package com.example.HystrixWithMDCDemo;

import com.example.HystrixWithMDCDemo.aspect.TransferHystrixThreadContext;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.MDC;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;

@Service
@EnableHystrix
public class TestService {

    @HystrixCommand
    @TransferHystrixThreadContext
    public void addLog() {
        MDC.put("hystrix-thread-test", "OK!");
    }
}
