package com.bambrow.async.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class SyncTasks {

    private static final Random random = new Random();

    public void runTask(int i) throws InterruptedException {
        log.info("SyncTasks.runTask: start task " + i);
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("SyncTasks.runTask: task " + i + " completed, time taken: " + (end - start) + "ms");
    }

}
