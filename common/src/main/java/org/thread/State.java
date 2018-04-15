package org.thread;

public enum State {

    /**
     * 0 空闲中
     * 1 工作中
     */
    Resting(0),Working(1),
    ;


    private int state;

    private State(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

}
