package com.kong.common.obj;

import com.kong.fsm.FSMMachine;

public class PlayerActor extends Performer {
    // ================AI相关属性=================================
    private FSMMachine<? extends MonsterActor> machine;

    private int aiType = 1;

    private boolean isDead = false;

    public boolean isDead(){
        return isDead;
    }

    public PlayerActor(long id, String name) {
        this.id = id;
        this.name = name;
        this.type = 1;
    }
}
