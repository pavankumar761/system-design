package org.example.mt.intercom;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : Pavan Kumar
 * @created : 05/04/26, Sunday
 */

public class ProducerConsumer {


    public static void main(String[] args) {

        final Object lock = new Object();
        Queue<Integer> queue = new LinkedList<Integer>();
        int size = 10;

        Thread producer = new Thread(new Runnable() {

            int count = 0;
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            while (queue.size() == size) lock.wait();
                            System.out.println("Im Producer :" + queue.size());
                            queue.offer(count++);
                            Thread.sleep(100);
                            lock.notifyAll();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            while (queue.isEmpty()) lock.wait();
                            queue.poll();
                            System.out.println("Im Consumer : " + queue.size());
                            Thread.sleep(100);
                            lock.notifyAll();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
