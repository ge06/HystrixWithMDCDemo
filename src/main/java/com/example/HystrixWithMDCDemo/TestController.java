package com.example.HystrixWithMDCDemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestService testService;

    @GetMapping
    public String testIt() {
        MDC.put("controller-test", "OK!");
        testService.addLog();
        log.info("Tested!");
        MDC.clear();
        return "OK!";
    }
}
