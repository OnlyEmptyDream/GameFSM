package com.kong.fsm.monster;

import com.kong.common.obj.MonsterActor;
import com.kong.fsm.FSMState;
import com.kong.fsm.monster.ai.AIFactory;
import com.kong.fsm.monster.ai.ObjectAI;

public class MonsterDieState extends FSMState<MonsterActor> {
    public MonsterDieState(int type, MonsterActor performer) {
        super(type, performer);
    }

    @Override
    public void enter() {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.dieEnter(performer);
    }

    @Override
    public void exit() {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.dieExit(performer);
    }

    @Override
    public void update(int delta) {
    }

    @Override
    public int checkTransition() {
        if(performer.getTempChangeStateType() != Die){
            System.out.println(performer.getName() + "受到" + performer.getTargetObject().getName() + "的影响，状态发生改变");
            return performer.getTempChangeStateType();
        }

//        if (!performer.isDead()) {
//            return Sleep;
//        }
        return Die;
    }
}
