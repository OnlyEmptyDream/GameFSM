package com.kong.behavior.impl;

import com.kong.behavior.BehaviorConst;
import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.IBehavior;
import com.kong.behavior.IComposite;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComposite extends BaseBehavior implements IComposite {

    protected List<IBehavior> childrens = new ArrayList<>();

    /**
     * 自身行为
     */
    protected List<IBehavior> services = new ArrayList<>();

    public BaseComposite(BehaviorTreeConfig config) {
        super(config);
    }


    @Override
    public void addChild(IBehavior child) {
        childrens.add(child);
    }

    @Override
    public List<IBehavior> getChildren() {
        return childrens;
    }

    @Override
    public void addService(IBehavior service){
        this.services.add(service);
    }

    @Override
    public BehaviorConst.EStatus update() {

        //先执行完service,再执行子节点
        for (IBehavior service : services){
            service.update();
        }

        return updateChilden();
    }

    public abstract BehaviorConst.EStatus updateChilden();

    @Override
    public void reset(){
        for (IBehavior behavior : childrens){
            behavior.reset();
        }

        for (IBehavior behavior : services){
            behavior.reset();
        }

        super.reset();
    }
}
