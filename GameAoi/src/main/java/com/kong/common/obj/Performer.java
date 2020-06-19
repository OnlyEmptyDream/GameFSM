package com.kong.common.obj;

import com.kong.common.obj.MapObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Performer extends MapObject {

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
