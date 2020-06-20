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
        for (MapObject watchersObject: watchers.values()) {
            System.out.println(watchersObject.getName() + "接收到消息：对象" + obj.getName() + "进入地图, 当前坐标" + obj.getVector3());
        }
        System.out.println("-------------------------------------------------------");
    }

    @Override
    public void onUpdate(MapObject obj, Map<Long, MapObject> removeWatchers, Map<Long, MapObject> updateWatchers, Map<Long, MapObject> addWachers) {
        for (MapObject watchersObject: removeWatchers.values()) {
            System.out.println(watchersObject.getName() + "接收到消息：对象" + obj.getName() + "离开视野, 当前坐标" + obj.getVector3());
        }
        for (MapObject watchersObject: updateWatchers.values()) {
            System.out.println(watchersObject.getName() + "接收到消息：对象" + obj.getName() + "更新坐标, 当前坐标" + obj.getVector3());
        }
        for (MapObject watchersObject: addWachers.values()) {
            System.out.println(watchersObject.getName() + "接收到消息：对象" + obj.getName() + "进入视野, 当前坐标" + obj.getVector3());
        }
        System.out.println("-------------------------------------------------------");
    }

    public void onUpdateWatcher(MapObject watcher, List<MapObject> removeObjectList, List<MapObject> addObjectList) {
        for (MapObject obj : removeObjectList) {
            System.out.println(watcher.getName() + "更新视野，" + obj.getName() + "离开视野," + watcher.getName() + "当前坐标" + watcher.getVector3());
        }
        for (MapObject obj : addObjectList) {
            System.out.println(watcher.getName() + "更新视野，" + obj.getName() + "进入视野," + watcher.getName() + " 当前坐标" + watcher.getVector3());
        }
        System.out.println("-------------------------------------------------------");
    }

    public void remove(MapObject obj, Map<Long, MapObject> watchers) {

    }
}
