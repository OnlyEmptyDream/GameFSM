package com.kong.aoi;

import com.kong.common.obj.MapObject;

import java.util.List;
import java.util.Map;

public interface AOIEventListener {
    //灯塔只是一个寄放object和观察者的容器

    /**
     * 视野内添加了对象事件
     *
     * @param obj
     * @param watchers
     */
    void onAdd(MapObject obj, Map<Long, MapObject> watchers);

    /**
     * 被观察者移动之后，通知观察者 该被观察者的位置变更
     * @param obj
     * @param oldWatchers
     * @param newWachers
     */
    void onUpdate(MapObject obj, Map<Long, MapObject> oldWatchers, Map<Long, MapObject> updateWatchers, Map<Long, MapObject> newWachers);

    /**
     * 观察者自己移动之后，视野更新事件
     * @param obj
     * @param addObjectList
     * @param removeObjectList
     */
    void onUpdateWatcher(MapObject obj, List<MapObject> removeObjectList, List<MapObject> addObjectList);


    /**
     * 视野中移除了对象事件 离开分两步 先是玩家离开地图 移除观测者观察， 后是玩家进入地图， 增加观测者
     *
     * @param obj
     * @param watchers
     */
    void remove(MapObject obj, Map<Long, MapObject> watchers);
}
