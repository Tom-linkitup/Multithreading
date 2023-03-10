package com.example.threadcreation;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    private final List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        List<Thread> threads = new ArrayList<>();
        for (Runnable task : tasks) {
            threads.add(new Thread(task));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
