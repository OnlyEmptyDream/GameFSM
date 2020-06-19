package com.kong.fsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 状态机中的状态
 * @param <T>
 */
public class FSMState<T extends Performer> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(FSMState.class);

    public static final int Sleep = 1;

    public static final int Active = 2;

    public static final int Fight = 3;

    public static final int Die = 4;

    public static final int Animate = 5;

    protected int type;

    /**
     * 执行者
     */
    protected T performer;

    public FSMState(int type, T performer){
        this.type = type;
        this.performer = performer;
    }

    /**
     * 进入状态
     *
     */
    public void enter() {

    }

    /**
     * 退出状态
     *
     */
    public void exit() {

    }

    /**
     * 更新状态
     *
     * @param delta
     */
    public void update(int delta) {
    }

    /**
     * 检查状态转换
     *
     * @return
     */
    public int checkTransition() {
        return 0;
    }

    /**
     * 拷贝原状态机休眠时间到当前状态机
     *
     * @param sleepTime
     */
    public void copyTime(int sleepTime) {

    }

    /**
     * 获取当前状态机休眠时间
     *
     * @return
     */
    public int getTime() {
        return 0;
    }
}
