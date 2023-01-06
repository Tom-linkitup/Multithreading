package com.example.threadcreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadCreationExtendsThread {

    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threadList = new ArrayList<>();
        threadList.add(new AscendingHacker(vault));
        threadList.add(new DescendingHacker(vault));
        threadList.add(new PoliceThread());

        for (Thread thread : threadList) {
            thread.start();
        }
    }

    private static class Vault {
        private int password;
        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrect(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;
        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHacker extends HackerThread {
        public AscendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_PASSWORD; i++) {
                if (vault.isCorrect(i)) {
                    System.out.println(this.getName() + " guessed the password " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHacker extends HackerThread {
        public DescendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = MAX_PASSWORD; i >= 0; i--) {
                if (vault.isCorrect(i)) {
                    System.out.println(this.getName() + " guessed the password " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {

        @Override
        public void run() {
           for (int i = 10; i > 0; i--) {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
               }
               System.out.println(i);
           }
           System.out.println("Game Over Hackers");
           System.exit(0);

        }
    }
}
