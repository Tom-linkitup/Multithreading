package com.example.threadcoordination;
/*
* We want to write a program that prints out all integers from 0 to Integer.MAX_VALUE,
* however we want to do it using two threads, where the first thread prints out all the even numbers,
* and the second thread prints out all the odd numbers.
* */
public class NumbersPrinter {

    public static void main(String[] args) throws InterruptedException {

        Coordinator coordinator = new Coordinator();

        Runnable evenPrinter = new EvenPrinterRunnable(coordinator);
        Runnable oddPrinter = new OddPrinterRunnable(coordinator);

        Thread thread1 = new Thread(evenPrinter);
        Thread thread2 = new Thread(oddPrinter);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public static class Coordinator {
        private int counter = 0;
    }

    public static class EvenPrinterRunnable implements Runnable  {

        private final Coordinator coordinator;
        public EvenPrinterRunnable(Coordinator coordinator) {
            this.coordinator = coordinator;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (coordinator) {
                    if (coordinator.counter % 2 == 0) {
                        try {
                            coordinator.wait();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(coordinator.counter);
                    coordinator.counter++;
                    coordinator.notify();
                }
            }
        }
    }

    public static class OddPrinterRunnable implements Runnable {

        private final Coordinator coordinator;
        public OddPrinterRunnable(Coordinator coordinator) {
            this.coordinator = coordinator;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (coordinator) {
                    if (coordinator.counter % 2 != 0) {
                        try {
                            coordinator.wait();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(coordinator.counter);
                    coordinator.counter++;
                    coordinator.notify();
                }
            }
        }
    }
}
