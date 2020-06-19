package com.kong.fsm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Performer {

    /**
     * 攻击我的对象
     */
    protected long whoAttackMe;
    protected long whoAttackMeTime;


    /**
     * 我的攻击目标
     */
    protected long whoMyTarget;

    protected long whoMyTargetTime;
}
