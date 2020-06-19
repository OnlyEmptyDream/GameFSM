package com.kong.aoi;

import com.kong.aoi.obj.IMapObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Tower {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tower.class);

    private final Map<Long, IMapObject> objectMap = new HashMap<>();

    private final Map<Long, IMapObject> watchers = new HashMap<>();
//    type 为MapObjectType里面的枚举类型
    private final Map<Integer, Map<Long, IMapObject>> typeOfObjectMap = new HashMap<>();

    /**
     * 灯塔中游戏对象的数量
     */
    private long size = 0;

    private int x = 0;

    private int y = 0;

    public boolean addObject(IMapObject obj) {
        if (obj == null) {
            return false;
        }
        objectMap.put(obj.getId(), obj);
        Map<Long, IMapObject> map = this.typeOfObjectMap.get(obj.getType());
        if (map == null) {
            map = new HashMap<>();
            this.typeOfObjectMap.put(obj.getType(), map);
        }
        if (map.containsKey(obj.getId())) {
            LOGGER.error("添加到灯塔重复：" + obj.getName() + "[" + this.x + "," + this.y + "]");
            return false;
        }
        map.put(obj.getId(), obj);
        if (obj.getType() == MapObjectType.Player) {
            LOGGER.debug("添加到灯塔成功：" + obj.getName() + "[" + this.x + "," + this.y + "]");
        }


        this.size++;
        return true;
    }
    /**
     * 添加一个观察者到灯塔中
     *
     * @param obj
     */
    public void addWatcher(IMapObject obj) {
        if (obj == null) {
            return;
        }
        this.watchers.put(obj.getId(), obj);
    }

    /**
     * 从灯塔中移除一个观察者
     *
     * @param obj
     */
    public void removeWatcher(IMapObject obj) {
        if (obj != null) {
            IMapObject ret = this.watchers.remove(obj.getId());
            if (ret == null) {
                LOGGER.debug("移除观察者失败：" + obj.getName() + "[" + this.x + "," + this.y + "]");
            } else {
                if (obj.getType() == MapObjectType.Player) {
                    LOGGER.debug("移除观察者成功：" + obj.getName() + "[" + this.x + "," + this.y + "]");
                }

            }
        }
    }

    /**
     * 获取所有观察者
     *
     * @return
     */
    public Map<Long, IMapObject> getWatchers() {
        return this.watchers;
    }

    /**
     * 从灯塔中移除一个对象
     *
     * @param obj
     */
    public void removeObject(IMapObject obj) {
        boolean isPlayer = obj.getType() == MapObjectType.Player;
        obj = objectMap.remove(obj.getId());
        if (obj != null) {
            if (this.typeOfObjectMap.containsKey(obj.getType())) {
                this.typeOfObjectMap.get(obj.getType()).remove(obj.getId());
            }
            this.size--;
            LOGGER.debug("{}移除灯塔成功：[{}-{}]", obj.getName(), this.x, this.y);
        } else {
            LOGGER.debug("移除灯塔失败，灯塔里没有这个对象：[{}-{}]", this.x, this.y);
        }
    }

    /**
     * 获取指定类型的ID列表
     *
     * @param type
     * @return
     */
    public Map<Long, IMapObject> getObjectByType(int type) {
        if (this.typeOfObjectMap.containsKey(type)) {
            return this.typeOfObjectMap.get(type);
        }
        return Collections.emptyMap();
    }

    /**
     * 获取所有游戏对象
     *
     * @return
     */
    public Map<Long, IMapObject> getAllObject() {
        return objectMap;
    }

    public void clear() {
        this.objectMap.clear();
        this.watchers.clear();
        this.typeOfObjectMap.clear();
    }

    public long size() {
        return size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void checkObject(int mapId) {
        Iterator<Long> allIt = objectMap.keySet().iterator();
        while (allIt.hasNext()) {
            Long id = allIt.next();
            IMapObject iMapObject = objectMap.get(id);
            if (iMapObject == null) {
                continue;
            }
            if (iMapObject.getVector3() == null) {
                LOGGER.error("地图{}中的对象{}坐标为空，但却依然在AOI.objectMap里,Tower:[{},{}]", mapId, iMapObject.getName(), this.x, this.y);
                allIt.remove();
            }
        }

        Iterator<Long> watcherIt = watchers.keySet().iterator();
        while (watcherIt.hasNext()) {
            Long id = watcherIt.next();
            IMapObject iMapObject = watchers.get(id);
            if (iMapObject == null) {
                continue;
            }
            if (iMapObject.getVector3() == null) {
                LOGGER.error("地图{}中的对象{}坐标为空，但却依然在AOI.watchers里,Tower:[{},{}]", mapId, iMapObject.getName(), this.x, this.y);
                watcherIt.remove();
            }
        }

        for (Map<Long, IMapObject> typeMap : typeOfObjectMap.values()) {
            Iterator<Long> typeIt = typeMap.keySet().iterator();
            while (typeIt.hasNext()) {
                Long id = typeIt.next();
                IMapObject iMapObject = typeMap.get(id);
                if (iMapObject == null) {
                    continue;
                }
                if (iMapObject.getVector3() == null) {
                    LOGGER.error("地图{}中的对象{}坐标为空，但却依然在AOI.typeOfObjectMap里,Tower:[{},{}]", mapId, iMapObject.getName(), this.x, this.y);
                    typeIt.remove();
                }
            }
        }
    }
}
