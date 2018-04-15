package org.thread;

public class Computor {

    /**
     * 标识
     */
    private Long id ;
    /**
     * 状态
     */
    private State state ;

    public Computor(ResourcesManager resourcesManager){
         //注册电脑
        resourcesManager.registerRestComputer(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
