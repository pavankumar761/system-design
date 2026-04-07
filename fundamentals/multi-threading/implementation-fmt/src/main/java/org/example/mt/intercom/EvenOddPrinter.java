package org.example.mt.intercom;

/**
 * @author : Pavan Kumar
 * @created : 07/04/26, Tuesday
 */

public class EvenOddPrinter {

    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        final Object lock = new Object();
        Counter counter = new Counter();
//        System.out.println("count : " + counter.count++ + " "  + counter.count);


        Thread oddPrinter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter.count < 20) {
                    synchronized (lock) {
                        try {
                            while (counter.count % 2 == 0) lock.wait();
                            System.out.println(counter.count++ + " Odd");
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        });

        Thread evenPrinter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter.count < 20) {
                    synchronized (lock) {
                        try {
                            while (counter.count % 2 == 1) lock.wait();
                            System.out.println(counter.count++ + " even");
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        });

        oddPrinter.start();
        evenPrinter.start();
    }
}


class Counter {
    int count = 0;
}
