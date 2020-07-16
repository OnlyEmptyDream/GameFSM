package com.kong.behavior.entity.composite;

import com.kong.behavior.BehaviorConst;
import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.IBehavior;
import com.kong.behavior.composite.ISequence;
import com.kong.behavior.impl.BaseComposite;

public class SequenceImpl extends BaseComposite implements ISequence {

    public SequenceImpl(BehaviorTreeConfig config) {
        super(config);
    }

    @Override
    public BehaviorConst.EStatus updateChilden() {

        BehaviorConst.EStatus curStatus = BehaviorConst.EStatus.Running;
        for (int i = 0 ; i < childrens.size(); i ++) {
            IBehavior behavior = childrens.get(i);

            if (behavior.getStatus() == BehaviorConst.EStatus.Success){
                continue;
            }

            if (behavior.isNegation()){
                continue;
            }

            curStatus = behavior.tick();

            if (curStatus == BehaviorConst.EStatus.Success){
                continue;
            }

            if (curStatus == BehaviorConst.EStatus.Aborted || curStatus ==  BehaviorConst.EStatus.Failure){
                break;
            }
        }

        return curStatus;
    }
}
