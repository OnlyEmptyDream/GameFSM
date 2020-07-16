package com.kong.common.obj;

import com.kong.cd.CD;
import com.kong.cd.CDObject;
import com.kong.common.obj.MapObject;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Performer extends MapObject implements CDObject {

    protected Map<String, CD> cdMap = new HashMap<>();

    /**
     * 攻击我的对象
     */
    protected long whoAttackMe;
    protected long whoAttackMeTime;

    protected long whoActiveMe;

    /**
     * 我的攻击目标
     */
    protected long whoMyTarget;

    protected long whoMyTargetTime;


}
