package com.kong.behavior;

import java.util.List;

/**
 * 复合节点，将多个节点进行组合
 * @author peanut
 * @date 2020/4/27  15:18
 **/
public interface IComposite extends IBehavior{

    List<IBehavior> getChildren();

    void addService(IBehavior service);
}
