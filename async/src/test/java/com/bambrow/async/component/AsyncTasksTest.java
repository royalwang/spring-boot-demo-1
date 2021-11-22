package com.bambrow.async.component;

import com.bambrow.async.utils.AsyncUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootTest
public class AsyncTasksTest {

    @Autowired
    private AsyncTasks tasks;

    @Test
    public void test() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> taskList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CompletableFuture<String> task = tasks.runTask(i);
            taskList.add(task);
        }
        // CompletableFuture.allOf(taskList.toArray(new CompletableFuture[taskList.size()])).join();
        AsyncUtils.allOf(taskList).join();
        long end = System.currentTimeMillis();
        log.info("AsyncTasksTest: all tasks completed, time taken: " + (end - start) + "ms");
    }

    // use void return type to invoke the custom exception handler
    @Test
    public void testWithException() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            tasks.runTaskWithException(i);
        }
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        log.info("AsyncTasksTest: all tasks completed, time taken: " + (end - start) + "ms");
    }

}
