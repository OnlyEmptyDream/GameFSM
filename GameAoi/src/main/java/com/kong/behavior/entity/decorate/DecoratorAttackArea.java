package com.kong.behavior.entity.decorate;


import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.impl.BaseDecorator;
import com.kong.path.GeomUtil;

/**
 * 是否在攻击范围内

 **/
public class DecoratorAttackArea extends BaseDecorator {

    public DecoratorAttackArea(BehaviorTreeConfig config) {
        super(config);
    }

    @Override
    public boolean isPass() {
        return true;
    }
}
