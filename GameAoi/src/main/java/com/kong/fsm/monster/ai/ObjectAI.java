package com.kong.fsm.monster.ai;

import com.kong.fsm.monster.MonsterActor;

public interface ObjectAI {
    boolean activeEnter(MonsterActor monster);
    boolean activeUpdate(MonsterActor monster, int dt); // dt间隔时间
    boolean activeExit(MonsterActor monster);

    boolean battleEnter(MonsterActor monster);
    boolean battleUpdate(MonsterActor monster, int dt);
    boolean battleExit(MonsterActor monster);

    boolean dieEnter(MonsterActor monster);
    boolean dieUpdate(MonsterActor monster, int dt);
    boolean dieExit(MonsterActor monster);

    boolean sleepEnter(MonsterActor monster);
    boolean sleepUpdate(MonsterActor monster, int dt);
    boolean sleepExit(MonsterActor monster);

    boolean animateEnter(MonsterActor monster);
    boolean animateUpdate(MonsterActor monster, int dt);
    boolean animateExit(MonsterActor monster);
}
