package org.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Mains {

    public static void main(String[] args) throws InterruptedException {

        int tasksize = 12;   //任务总数

        AtomicInteger atomicInteger = new AtomicInteger(tasksize+1);
        ResourcesManager resourcesManager = new ResourcesManagerImpl();

        Computor computor1 = new  Computor(resourcesManager);computor1.setId(1L);computor1.setState(State.Resting);
        Computor computor2 = new  Computor(resourcesManager);computor2.setId(2L);computor2.setState(State.Resting);
        Computor computor3 = new  Computor(resourcesManager);computor3.setId(3L);computor3.setState(State.Resting);


        Keyboard keyboard1  = new Keyboard(resourcesManager);keyboard1.setId(1L);keyboard1.setState(State.Resting);
        Keyboard keyboard2  = new Keyboard(resourcesManager);keyboard2.setId(2L);keyboard2.setState(State.Resting);
        Keyboard keyboard3  = new Keyboard(resourcesManager);keyboard3.setId(3L);keyboard3.setState(State.Resting);
        Keyboard keyboard4  = new Keyboard(resourcesManager);keyboard4.setId(4L);keyboard4.setState(State.Resting);

        Employee employee1 = new Employee(resourcesManager);employee1.setId(1l);employee1.setState(State.Resting);
        Employee employee2 = new Employee(resourcesManager);employee2.setId(2l);employee2.setState(State.Resting);
        Employee employee3 = new Employee(resourcesManager);employee3.setId(3l);employee3.setState(State.Resting);
        Employee employee4 = new Employee(resourcesManager);employee4.setId(4l);employee4.setState(State.Resting);
        Employee employee5 = new Employee(resourcesManager);employee5.setId(5l);employee5.setState(State.Resting);
        Employee employee6 = new Employee(resourcesManager);employee6.setId(6l);employee6.setState(State.Resting);



        Thread child1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                int i = atomicInteger.decrementAndGet();
                while (i >0){
                    employee1.startWork();
                    i = atomicInteger.decrementAndGet();
                }


            }
        });

        Thread child2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                int i = atomicInteger.decrementAndGet();
                while (i >0){
                    employee2.startWork();
                    i = atomicInteger.decrementAndGet();
                }
            }
        });

        Thread child3 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = atomicInteger.decrementAndGet();
                while (i >0){
                    employee3.startWork();
                    i = atomicInteger.decrementAndGet();
                }
            }
        });
        Thread child4 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = atomicInteger.decrementAndGet();
                while (i >0){
                    employee4.startWork();
                    i = atomicInteger.decrementAndGet();
                }
            }
        });
        Thread child6 =  new Thread(new Runnable() {
            @Override
            public void run() {
                int i = atomicInteger.decrementAndGet();
                while (i >0){
                    employee5.startWork();
                    i = atomicInteger.decrementAndGet();
                }
            }
        });
        Thread child5= new Thread(new Runnable() {
            @Override
            public void run() {
                int i = atomicInteger.decrementAndGet();
                while (i >0){
                    employee6.startWork();
                    i = atomicInteger.decrementAndGet();
                }
            }
        }) ;

        child1.start();child2.start();child3.start();child4.start();child5.start();child6.start();

        child1.join();child2.join();child3.join();child4.join();child5.join();child6.join();
    }

}
