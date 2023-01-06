package com.example.threadcoordination;

public class NumberCharPrint {

    private int printCharFlag = 1;
    private int count = 1;

    public void printNumber() {
        synchronized (this) {
            if (printCharFlag != 1) {
                // print number
                try {
                    this.wait();
                } catch (InterruptedException e) {
                }
            }
            System.out.print(2 * count - 1);
            System.out.print(2 * count);
            printCharFlag = 2;
            this.notify();
        }

    }

    public void printChar() {
        synchronized (this) {
            if (printCharFlag != 2) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                }
            }
            System.out.print((char) (count - 1 + 'A'));
            count++;
            printCharFlag = 1;
            this.notify();
        }
    }

    public static void main(String[] args) {
        NumberCharPrint numberCharPrint = new NumberCharPrint();
        new Thread(() -> {
            for(int i = 0; i < 26; i++) {
                numberCharPrint.printNumber();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                numberCharPrint.printChar();
            }
        }).start();

    }
}
