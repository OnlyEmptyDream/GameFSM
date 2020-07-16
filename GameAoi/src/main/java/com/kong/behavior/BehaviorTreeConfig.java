package com.kong.behavior;

import com.sh.common.config.AbstractConfigData;

@lombok.Getter
@lombok.Setter
public class BehaviorTreeConfig extends AbstractConfigData {

    private int id;
    private int type;
    private int subType;
    private String desc;
    private int[] nextId;
    private int[] decorators;
    private int[] value;
}
