package org.example.mt.intercom;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author : Pavan Kumar
 * @created : 07/04/26, Tuesday
 */

public class BlockedQueuePC {

    public static void main(String[] args) {
        System.out.println("Lets Start");
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        Thread producer = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (true) {
                    try {
                        blockingQueue.put(count++);
                        System.out.println("Im Producer : " + blockingQueue.size());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        blockingQueue.take();
                        System.out.println("Im consumer : " + blockingQueue.size());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        producer.start();
        consumer.start();
    }

}
