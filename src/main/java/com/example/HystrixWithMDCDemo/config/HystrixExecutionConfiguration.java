package com.example.HystrixWithMDCDemo.config;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HystrixExecutionConfiguration extends HystrixCommandExecutionHook {

    private Map<String, String> sharedContextMap = new HashMap<>();

    public HystrixExecutionConfiguration() {
        HystrixPlugins.getInstance().registerCommandExecutionHook(this);
    }

    @Override
    public <T> void onThreadComplete(HystrixInvokable<T> commandInstance) {
        MDC.getCopyOfContextMap().forEach((k,v) -> sharedContextMap.put(k,v));
        super.onThreadComplete(commandInstance);
    }

    @Override
    public <T> void onStart(HystrixInvokable<T> commandInstance) {
        sharedContextMap = MDC.getCopyOfContextMap();
        super.onStart(commandInstance);
    }

    public Map<String, String> getSharedContextMap() {
        return new HashMap<>(sharedContextMap);
    }

    public void clearSharedContext() {
        sharedContextMap.clear();
    }
}
