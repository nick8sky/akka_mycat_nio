package org.thread;

import java.util.Date;

public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread child1  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child1 done");
            }
        });
        Thread child2  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child2 done");
            }
        });


        System.out.println(new Date());
        child1.start();
        System.out.println(new Date());
        child2.start();
        System.out.println(new Date());
        child1.join();
        System.out.println(new Date());
        child2.join();
        System.out.println(new Date());
    }
}
