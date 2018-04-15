package org.thread;

import java.util.concurrent.*;

public class ResourcesManagerImpl implements  ResourcesManager{

    ConcurrentLinkedQueue<Computor> computers  = new ConcurrentLinkedQueue<Computor>();
    ConcurrentLinkedQueue<Keyboard> keyboards  = new ConcurrentLinkedQueue<Keyboard>();
    ConcurrentLinkedQueue<Employee> employees  = new ConcurrentLinkedQueue<Employee>();



    @Override
    public void registerRestComputer(Computor computor) {
        computers.add(computor);
    }

    @Override
    public void registerRestKeyboard(Keyboard keyboard) {
        keyboards.add(keyboard);
    }

    @Override
    public void registerRestEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void allocationResource(Employee employee) {
        while(employee.getComputor()==null || employee.getKeyboard() == null){
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
            }
        }

    }

    @Override
    public Keyboard getRestKeyboard() {
        return keyboards.poll();
    }

    @Override
    public Computor getRestComputor() {
        return computers.poll();
    }
}
