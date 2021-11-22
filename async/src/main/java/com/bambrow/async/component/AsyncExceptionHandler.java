package com.bambrow.async.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.info("AsyncExceptionHandler exception message: " + throwable.getMessage());
        log.info("AsyncExceptionHandler method name: " + method.getName());
        log.info("AsyncExceptionHandler parameter value: " + Arrays.toString(objects));
    }
}
