package com.kong.common.obj;

import com.kong.fsm.FSMMachine;
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

    public MonsterActor(long id, String name) {
        this.id = id;
        this.name = name;
        this.type = 2;
    }
}
