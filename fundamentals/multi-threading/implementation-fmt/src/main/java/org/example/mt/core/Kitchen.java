package org.example.mt.core;

/**
 * @author : Pavan Kumar
 * @created : 05/04/26, Sunday
 */

public class Kitchen implements Runnable {

    @Override
    public void run() {
        System.out.println("\n\nOrder Taken");
        try {
            prepareFood();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void prepareFood() throws InterruptedException {

        System.out.println("Entered Kitchen");

        synchronized ("Lock") {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Food Prep Started");
            Thread.sleep(3000);
            System.out.println("Food Prep Done");
        }
    }


    public static void main(String[] args) {
        System.out.println("Hello, World!");
//        Thread thread = new Thread(new Kitchen());
//        thread.start();
//        Thread thread1 = new Thread(new Kitchen());
//        thread1.start();
//        Thread thread2 = new Thread(new Kitchen());
//        thread2.start();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Kitchen.prepareFood();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        t0.start();t1.start();

    }
}
