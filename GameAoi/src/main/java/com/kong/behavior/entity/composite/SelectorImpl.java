package com.kong.behavior.entity.composite;

import com.kong.behavior.BehaviorConst;
import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.IBehavior;
import com.kong.behavior.composite.ISelector;
import com.kong.behavior.impl.BaseComposite;

public class SelectorImpl extends BaseComposite implements ISelector {


    public SelectorImpl(BehaviorTreeConfig config) {
        super(config);
    }

    @Override
    public BehaviorConst.EStatus updateChilden() {

        BehaviorConst.EStatus curStatus = BehaviorConst.EStatus.Running;
        for (int i = 0 ; i < childrens.size(); i ++) {
            IBehavior behavior = childrens.get(i);

            if (behavior.isNegation()){
                continue;
            }

            curStatus = behavior.tick();
//            if (curStatus == BehaviorConst.EStatus.Aborted ||
//                curStatus == BehaviorConst.EStatus.Failure ||
//                curStatus == BehaviorConst.EStatus.Success){
//                break;
//            }
            break;
        }

        return curStatus;
    }
}
