package com.kong.fsm;

import com.kong.common.obj.Performer;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态机，用于控制AI等，包括玩家的战斗状态
 */
@Setter
@Getter
public class FSMMachine<T extends Performer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FSMMachine.class);

    /**
     * 可用状态
     */
    private List<FSMState<T>> states = new ArrayList<>();

    /**
     * 当前状态
     */
    private FSMState<T> state;

    /**
     * 是否正在运行（这里只至少update一次以后才算运行）
     */
    private boolean running = false;

    private AIData aiData = new AIData();

    public FSMMachine(List<FSMState<T>> states, FSMState<T> init) {
        this.states.addAll(states);
        this.state = init;
        this.aiData.setClearThreatTime(5000);
    }

    public void updateMachine(int delta) {

        if (states.isEmpty()) {
            return;
        }

        if (this.state == null) {
            return;
        }
        int curType = this.state.type;
        int nextType = this.state.checkTransition();
        if (nextType != curType) {
            FSMState<T> nextState = getStateByType(nextType);
            if (nextState != null) {
                // 复制时间
                int sleepTime = this.state.getTime();
                this.state.exit();
                this.state = nextState;
                this.state.enter();
                this.state.copyTime(sleepTime);
                return;
            }
        }
        this.state.update(delta);
        this.running = true;
    }

    public FSMState<T> getStateByType(int goal) {
        for (FSMState<T> state : states) {
            if (state.type == goal) {
                return state;
            }
        }
        return null;
    }

    public boolean isRunning() {
        return running;
    }

}
