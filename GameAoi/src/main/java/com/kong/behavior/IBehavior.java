package com.kong.behavior;


import com.kong.common.obj.MonsterActor;
import com.kong.common.obj.Performer;

public interface IBehavior {

    /**
     * 设置调用顺序，onInitialize--update--onTerminate
     * @return
     */
    BehaviorConst.EStatus tick();

    /**
     * 当节点调用前
     */
    void onInitialize();

    /**
     * 节点调用后执行
     * @param Status
     */
    void onTerminate(BehaviorConst.EStatus Status);

    /**
     * 释放对象所占资源
     */
    void release();

    /**
     * 增加子节点
     * @param child
     */
    void addChild(IBehavior child);

    /**
     * 设置状态
     */
    void setStatus(BehaviorConst.EStatus status);

    BehaviorConst.EStatus getStatus();

    void reset();

    void setPerformer(MonsterActor monsterActor);

    void addDecorator(IBehavior decorator);

    boolean isNegation();

    BehaviorConst.EStatus update();
}
