package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        PrintThread printThread1 = new PrintThread("+");
        printThread1.start();
        PrintThread printThread2 = new PrintThread("-");
        printThread2.start();
        Thread.sleep(2000);
        printThread2.flag = false;
        System.out.print("stop");
    }
    public static Object key = new Object();
    public static void testPrint(String s) {
        synchronized (key) {
            try {
                Thread.sleep(200);
                System.out.print("[");
                Thread.sleep(200);
                System.out.print(s);
                Thread.sleep(200);
                System.out.print("]");
                //key.notify();
                //key.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintThread extends Thread {
    private String message;
    public boolean flag = true;
    public PrintThread(String message) {
        this.message = message;
    }
    @Override
    public void run() {
        while (flag) {
            Main.testPrint(message);
        }
    }
}