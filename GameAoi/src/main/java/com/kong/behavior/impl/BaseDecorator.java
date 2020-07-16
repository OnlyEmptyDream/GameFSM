package com.kong.behavior.impl;


import com.kong.behavior.BehaviorConst;
import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.IBehavior;
import com.kong.behavior.IDecorator;

public abstract class BaseDecorator extends BaseBehavior implements IDecorator {

    public BaseDecorator(BehaviorTreeConfig config) {
        super(config);
    }

    public abstract boolean isPass();

    @Override
    public boolean isNegation(){
        return !isPass();
    }

    @Override
    public void addChild(IBehavior child) {

    }

    @Override
    public BehaviorConst.EStatus update() {
        return null;
    }
}
