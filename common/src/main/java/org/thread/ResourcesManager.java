package org.thread;

public interface ResourcesManager {

    /**
     * 注册电脑
     */
    public  void registerRestComputer(Computor computor);

    /**
     * 注册键盘
     */
    public  void registerRestKeyboard(Keyboard keyboard);


    /**
     * 注册员工
     */
    public  void registerRestEmployee(Employee employee);


    /**
     * 分配资源
     * 如果employee的电脑和键盘可用，则employee.notify()
     */
    public void allocationResource(Employee employee);


    /**
     * 分配可用键盘
     */
    public Keyboard getRestKeyboard();

    /**
     * 分配可用电脑
     */
    public Computor getRestComputor();

}
