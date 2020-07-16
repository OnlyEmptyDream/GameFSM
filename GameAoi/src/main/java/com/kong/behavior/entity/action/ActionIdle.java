package com.kong.behavior.entity.action;

import com.kong.behavior.BehaviorConst;
import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.impl.BaseAction;
import lombok.extern.slf4j.Slf4j;

/**
 * 待机
 **/
@Slf4j
public class ActionIdle extends BaseAction {

    private long startTime;

    public ActionIdle(BehaviorTreeConfig config) {
        super(config);
    }

    @Override
    public BehaviorConst.EStatus update() {
        long now = System.currentTimeMillis();
        if (startTime == 0){
            this.startTime = now;
        }

        if (config.getValue() == null || config.getValue().length == 0){
            log.error("BehaviorTreeConfig配置错误,id:" + config.getId());
            return BehaviorConst.EStatus.Aborted;
        }
        return now - startTime >= config.getValue()[0] * 1000 ? BehaviorConst.EStatus.Success : BehaviorConst.EStatus.Running;
    }

    @Override
    public BehaviorConst.ActionType getType() {
        return BehaviorConst.ActionType.IDLE;
    }

    @Override
    public void resetData() {
        this.startTime = 0;
    }
}
