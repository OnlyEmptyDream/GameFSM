package com.kong.fsm.monster;

import com.kong.fsm.AIData;
import com.kong.fsm.FSMMachine;
import com.kong.fsm.Performer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterActor extends Performer {
    // ================AI相关属性=======================================
    private FSMMachine<? extends MonsterActor> machine;

    private int aiType = 1;

    private boolean isDead = false;

    public boolean isDead(){
        return isDead;
    }
}
