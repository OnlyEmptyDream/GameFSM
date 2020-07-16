package com.kong.behavior.impl;


import com.kong.behavior.BehaviorConst;
import com.kong.behavior.BehaviorFactory;
import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.IAction;

public abstract class BaseAction extends BaseBehavior implements IAction {


    public BaseAction(BehaviorTreeConfig config) {
        super(config);
    }

    public abstract BehaviorConst.ActionType getType();

    /**
     * 重置行为数据
     */
    public abstract void resetData();

    @Override
    public void reset(){
        this.resetData();
        super.reset();
    }
}
