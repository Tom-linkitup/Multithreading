package com.example.threadcreation;

public class ThreadCreationRunnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread =  new Thread(() -> {
            System.out.println("We are in thread " + Thread.currentThread().getName());
            System.out.println("Thread priority is " + Thread.currentThread().getPriority());
        });

        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread " + Thread.currentThread().getName() + " before thread");
        thread.start();
        System.out.println("We are in thread " + Thread.currentThread().getName() + " after thread");
    }
}
