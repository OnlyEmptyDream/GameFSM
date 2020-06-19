package com.kong.fsm.heart;

import com.kong.common.obj.MonsterActor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterHeart implements  Runnable{
    public MonsterHeart(MonsterActor monster) {
        this.monster = monster;
    }
    public MonsterActor monster;
    public long lastUpdateTime = System.currentTimeMillis();
    @Override
    public void run() {
        long curTime = System.currentTimeMillis();
        int delta = (int) (curTime - lastUpdateTime);
        lastUpdateTime = curTime;
        monster.getMachine().updateMachine(delta);
    }
}
