package com.kong.common.obj;

import com.kong.behavior.BehaviorTree;
import com.kong.fsm.FSMMachine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterActor extends Performer {
    // ================AI相关属性=======================================
    private FSMMachine<? extends MonsterActor> machine;
    private int aiType = 1; //状态机AI类型

    private int behaviorType = 1; //行为树AI类型
    protected BehaviorTree behaviorTree;//行为树根节点

    private boolean isDead = false;

    public boolean isDead(){
        return isDead;
    }

    public MonsterActor(long id, String name) {
        this.id = id;
        this.name = name;
        this.type = 2;
    }
}
