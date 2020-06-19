package com.kong.fsm;

import com.kong.common.obj.Performer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIData {
    /**
     * 已经睡眠的时间，整个AI系统都要用，主要是用于战斗和移动
     */
    private int sleepTime = 0;

    /**
     * 仇恨清除时间
     */
    private int clearThreatTime = 5000;

    /**
     * 死亡时间
     */
    private long dieTime = 0;

    /**
     * 下一次复活时间
     */
    private long nextReliveTime = 0;


    /**
     * 下次满血时间
     */
    private long nextFullHpTime;

    private boolean findPlayer;


    public void updateFightAITime(int dt) {
        this.clearThreatTime += dt;
        this.sleepTime += dt;
    }

    public void updateActiveAITime(int dt) {
        this.sleepTime += dt;
    }

    public void updatePetFollowAITime(int dt) {
        this.sleepTime += dt;
    }

    public void updatePetFightAITime(int dt) {
        this.sleepTime += dt;
    }

    public void updateMyTargetOrAttackedMe(Performer performer, int dt) {

        if (performer.getWhoAttackMe() > 0) {
            performer.setWhoAttackMeTime(performer.getWhoAttackMeTime() - dt);
            if (performer.getWhoAttackMeTime() <= 0) {
                performer.setWhoAttackMe(0);
            }

        }

        if (performer.getWhoMyTarget() > 0) {
            performer.setWhoMyTargetTime(performer.getWhoMyTargetTime() - dt);
            if (performer.getWhoMyTargetTime() <= 0) {
                performer.setWhoMyTarget(0);
            }
        }
    }

    public void clearMyTargetOrAttackedMe(Performer performer) {

        performer.setWhoAttackMeTime(0);
        performer.setWhoAttackMe(0);


        performer.setWhoMyTargetTime(0);
        performer.setWhoMyTarget(0);
    }
}
