package com.kong.fsm.monster;

import com.kong.common.obj.MonsterActor;
import com.kong.fsm.AIData;
import com.kong.fsm.FSMState;
import com.kong.fsm.monster.ai.AIFactory;
import com.kong.fsm.monster.ai.ObjectAI;

public class MonsterSleepState extends FSMState<MonsterActor> {
    public MonsterSleepState(int type, MonsterActor performer) {
        super(type, performer);
    }

    @Override
    public void enter() {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.sleepEnter(performer);
    }

    @Override
    public void exit() {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.sleepExit(performer);
    }

    @Override
    public void update(int delta) {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.sleepUpdate(performer, delta);
    }

    @Override
    public int checkTransition() {
        //这里根据AIData或者performer的字段及方法 判断应该回溯到哪个状态
        AIData aiData = performer.getMachine().getAiData();
        if (aiData.isFindPlayer()) {
            return Active;
        }
        return Sleep;
    }
}
