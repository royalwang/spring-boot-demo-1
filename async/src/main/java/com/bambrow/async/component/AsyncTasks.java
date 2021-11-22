package com.bambrow.async.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class AsyncTasks {

    private static final Random random = new Random();

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> runTask(int i) throws InterruptedException {
        log.info("AsyncTasks.runTask: start task " + i);
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("AsyncTasks.runTask: task " + i + " completed, time taken: " + (end - start) + "ms");
        return CompletableFuture.completedFuture("AsyncTasks.runTask: task " + i + " completed");
    }

    @Async
    public void runTaskWithException(int i) throws Exception {
        log.info("AsyncTasks.runTaskWithException: start task " + i);
        long start = System.currentTimeMillis();
        int sleepTime = random.nextInt(10000);
        if (sleepTime > 5000) {
            throw new Exception("AsyncTasks.runTaskWithException: sleep time " + sleepTime + " is larger than 5000");
        }
        Thread.sleep(sleepTime);
        long end = System.currentTimeMillis();
        log.info("AsyncTasks.runTaskWithException: task " + i + " completed, time taken: " + (end - start) + "ms");
    }

}
