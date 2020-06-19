package com.kong.fsm.monster;

import com.kong.common.obj.MonsterActor;
import com.kong.fsm.FSMState;
import com.kong.fsm.monster.ai.AIFactory;
import com.kong.fsm.monster.ai.ObjectAI;

public class MonsterFightState extends FSMState<MonsterActor> {
    public MonsterFightState(int type, MonsterActor performer) {
        super(type, performer);
    }

    @Override
    public void enter() {
        // 进入战斗状态，清除战斗记录
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.battleEnter(performer);
    }

    @Override
    public void exit() {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.battleExit(performer);
    }

    @Override
    public void update(int delta) {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.battleUpdate(performer, delta);
    }

    @Override
    public int checkTransition() {
        if (performer.isDead()) {
            return Die;
        }

        if (performer.getWhoAttackMe() != 0 || performer.getWhoMyTarget() != 0) {
            // 仇恨列表不为空，继续停留在战斗状态下
            return Fight;
        }
        // 否则进入活跃状态
        return Active;
    }
}
