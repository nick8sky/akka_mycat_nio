package org.thread;

public class Employee {

    /**
     * 标识
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     */
    private State state ;// = State.Resting;

    /**
     * 资源管理器
     */
    private ResourcesManager resourcesManager;

    /**
     * 工作电脑
     */
    private Computor computor;
    /**
     * 工作键盘
     */
    private Keyboard keyboard;


    public Employee(ResourcesManager resourcesManager) {
        this.resourcesManager = resourcesManager;
        //注册员工
        resourcesManager.registerRestEmployee(this);
    }


    /**
     * 获取电脑
     */
    public Computor getComputor() {
        if (computor == null)
            computor = resourcesManager.getRestComputor();
        return computor;
    }

    /**
     * 获取键盘
     */
    public Keyboard getKeyboard() {
        if (keyboard == null)
            keyboard = resourcesManager.getRestKeyboard();
        return keyboard;
    }

    /**
     * 归还电脑
     */
    private void releaseComputor() {
        resourcesManager.registerRestComputer(computor);
        this.computor = null;
    }

    /**
     * 归还键盘
     */
    private void releaseKeyboard() {
        resourcesManager.registerRestKeyboard(keyboard);
        this.keyboard = null;
    }


    public void startWork() {
        if (this.state.equals(State.Resting)) {
            resourcesManager.allocationResource(this);
            doWork();
        }

    }

    private void doWork() {
        this.state = State.Working;
        /**
         * do something ...
         */
        System.out.println(this.id +"_____" +this.computor.getId() +"____" +this.keyboard.getId());
        //归还电脑
        releaseComputor();
        //归还键盘
        releaseKeyboard();
        //释放状态
        this.state = State.Resting;

    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(State state) {
        this.state = state;
    }
}
