package com.kong.fsm.monster;

import com.kong.fsm.FSMState;
import com.kong.fsm.monster.ai.AIFactory;
import com.kong.fsm.monster.ai.ObjectAI;

public class MonsterActiveState extends FSMState<MonsterActor> {
    public MonsterActiveState(int type, MonsterActor performer) {
        super(type, performer);
    }

    @Override
    public void enter() {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.activeEnter(performer);
    }

    @Override
    public void exit() {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.activeExit(performer);
    }

    @Override
    public void update(int delta) {
        int aiType = performer.getAiType();
        ObjectAI objectAI = AIFactory.getAI(aiType);
        objectAI.activeUpdate(performer, delta);
    }

    @Override
    public int checkTransition() {
        if (performer.isDead()) {// 瞬秒（还没来得及进入战斗状态）
            return Fight;
        }

        if (performer.getWhoAttackMe() != 0 || performer.getWhoMyTarget() != 0) {
            // 仇恨列表不为空，继续停留在战斗状态下
            return Fight;
        }

        return Active;
    }
}
