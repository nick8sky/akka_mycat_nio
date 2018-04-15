package org.thread;

public class Keyboard {


    /**
     * 标识
     */
    private Long id;
    /**
     * 状态
     */
    private State state;


    public Keyboard(ResourcesManager resourcesManager) {
        //注册键盘
        resourcesManager.registerRestKeyboard(this);
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
