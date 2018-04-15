package org.thread;

import java.util.concurrent.*;

public class SemaphoreTest {


    public static void main(String[] args) throws InterruptedException {

        int N = 3;
        int M = 5;
        int Y = 12;

        BlockingQueue<String> computers = new LinkedBlockingQueue<String>(N);
        BlockingQueue<String> keyboards = new LinkedBlockingQueue<String>(M);
        BlockingQueue<String> employees = new LinkedBlockingQueue<String>(Y);
        ExecutorService threadPool = Executors.newFixedThreadPool(N);

        computers.put("test1"); computers.put("test2"); computers.put("test3");
        keyboards.put("k1");keyboards.put("k2");keyboards.put("k3");keyboards.put("k4");keyboards.put("k5");
        employees.put("e1");employees.put("e2");employees.put("e3");employees.put("e4");employees.put("e5");employees.put("e6");
        employees.put("e7");employees.put("e8");employees.put("e9");employees.put("e10");employees.put("e11");employees.put("e12");

        for (; ; )
            try {
                String computer = computers.take();
                String keyboard = keyboards.take();
                String employee = employees.take();

                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(employee + "   do something ...on " + computer +" " +keyboard);
                        try {
                            computers.put(computer);
                            keyboards.put(keyboard);
                            employees.put(employee);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
