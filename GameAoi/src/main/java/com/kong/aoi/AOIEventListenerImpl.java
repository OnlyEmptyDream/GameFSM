package com.kong.aoi;

import com.kong.common.obj.MapObject;

import java.util.List;
import java.util.Map;

public class AOIEventListenerImpl implements AOIEventListener{
    //发送消息给其他观测者（玩家）， 此对象的位置发生了改变

    // listener 目前只是一个单例，不管新建地图 还是新建副本， 虽然代码在遍历时 用了一个list存储listener，但未找到listener新的实例

    private static AOIEventListenerImpl INSTANCE = new AOIEventListenerImpl();

    private AOIEventListenerImpl() {

    }

    public static AOIEventListenerImpl getInstance() {
        return INSTANCE;
    }

    public void onAdd(MapObject obj, Map<Long, MapObject> watchers) {

    }

    public void onUpdate(MapObject obj, Map<Long, MapObject> oldWatchers, Map<Long, MapObject> newWachers) {

    }

    public void onUpdateWatcher(MapObject obj, List<MapObject> addObjectList, List<MapObject> removeObjectList) {

    }

    public void remove(MapObject obj, Map<Long, MapObject> watchers) {

    }
}
