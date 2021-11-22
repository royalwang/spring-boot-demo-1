package com.bambrow.async.component;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SyncTasksTest {

    @Autowired
    private SyncTasks tasks;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            tasks.runTask(i);
        }
    }

}
