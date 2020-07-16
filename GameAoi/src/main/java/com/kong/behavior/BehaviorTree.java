package com.kong.behavior;

public class BehaviorTree {
    private IBehavior root;

    public BehaviorTree(IBehavior root) {
        this.root = root;
    }

    public void tick() {
        BehaviorConst.EStatus status = root.tick();
        if (status != BehaviorConst.EStatus.Invalid && status != BehaviorConst.EStatus.Running){
            root.reset();
        }
    }

    public void end(){
        root.reset();
    }
}
