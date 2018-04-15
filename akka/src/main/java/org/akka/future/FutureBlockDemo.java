package org.akka.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureBlockDemo {
    private static void block(){

        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };

        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    private static void block2(){

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        block();
        block2();
    }


}
