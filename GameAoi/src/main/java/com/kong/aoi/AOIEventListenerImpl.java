package com.kong.aoi;

import com.kong.aoi.obj.IMapObject;

import java.util.List;
import java.util.Map;

public class AOIEventListenerImpl implements AOIEventListener{
    //发送消息给其他观测者（玩家）， 此对象的位置发生了改变

    private static AOIEventListenerImpl INSTANCE = new AOIEventListenerImpl();

    private AOIEventListenerImpl() {

    }

    public static AOIEventListenerImpl getInstance() {
        return INSTANCE;
    }

    public void onAdd(IMapObject obj, Map<Long, IMapObject> watchers) {

    }

    public void onUpdate(IMapObject obj, Map<Long, IMapObject> oldWatchers, Map<Long, IMapObject> newWachers) {

    }

    public void onUpdateWatcher(IMapObject obj, List<IMapObject> addObjectList, List<IMapObject> removeObjectList) {

    }

    public void remove(IMapObject obj, Map<Long, IMapObject> watchers) {

    }
}
