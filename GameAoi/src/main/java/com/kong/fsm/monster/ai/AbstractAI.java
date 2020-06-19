package com.kong.fsm.monster.ai;

import com.kong.fsm.monster.MonsterActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractAI implements ObjectAI {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractAI.class);

    @Override
    public boolean activeEnter(MonsterActor monster) {
        System.out.println("进入Acive状态");
        return true;
    }

    @Override
    public boolean activeUpdate(MonsterActor monster, int dt) {
        System.out.println("持续Acive状态");
        return true;
    }

    @Override
    public boolean activeExit(MonsterActor monster) {
        System.out.println("退出Acive状态");
        return true;
    }

    @Override
    public boolean battleEnter(MonsterActor monster) {
        System.out.println("进入战斗状态");
        return true;
    }

    @Override
    public boolean battleUpdate(MonsterActor monster, int dt) {
        System.out.println("持续战斗状态");
        return true;
    }

    @Override
    public boolean battleExit(MonsterActor monster) {
        System.out.println("退出战斗状态");
        return true;
    }

    @Override
    public boolean dieEnter(MonsterActor monster) {
        System.out.println("进入死亡状态");
        return true;
    }

    @Override
    public boolean dieUpdate(MonsterActor monster, int dt) {
        System.out.println("持续死亡状态");
        return true;
    }

    @Override
    public boolean dieExit(MonsterActor monster) {
        System.out.println("退出死亡状态");
        return true;
    }

    @Override
    public boolean sleepEnter(MonsterActor monster) {
        System.out.println("进入休眠状态");
        return true;
    }

    @Override
    public boolean sleepUpdate(MonsterActor monster, int dt) {
        System.out.println("持续休眠状态");
        return true;
    }

    @Override
    public boolean sleepExit(MonsterActor monster) {
        System.out.println("退出休眠状态");
        return true;
    }

    @Override
    public boolean animateEnter(MonsterActor monster) {
        System.out.println("进入动画状态");
        return true;
    }

    @Override
    public boolean animateUpdate(MonsterActor monster, int dt) {
        System.out.println("持续动画状态");
        return true;
    }

    @Override
    public boolean animateExit(MonsterActor monster) {
        System.out.println("退出动画状态");
        return true;
    }
}
