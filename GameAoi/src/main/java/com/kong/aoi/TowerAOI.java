package com.kong.aoi;

import com.kong.aoi.obj.Vector2f;
import com.kong.aoi.obj.Vector3f;
import com.kong.common.obj.MapObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Data
public class TowerAOI {
    private static final Logger LOGGER = LoggerFactory.getLogger(TowerAOI.class);

    public static final int WIDTH = 1;
    public static final int HEIGHT = 1;
    public static final int RANGE_LIMIT = 5;

    public static final int RANGE_DEFAULT = 5;

    /**
     * 原点坐标x
     */
    private int orginX = 0;

    /**
     * 原点坐标y
     */
    private int orginY = 0;

    /**
     * 地图宽度
     */
    private float width;

    /**
     * 地图高度
     */
    private float height;

    /**
     * 灯塔的宽度
     */
    private int towerWidth;

    /**
     * 灯塔的高度
     */
    private int towerHeight;

    /**
     * 遍历的时候最大返回限制
     */
    private int rangeLimit;

    /**
     * x最大值
     */
    private int maxX;

    /**
     * y最大值
     */
    private int maxY;

    private int mapId;

    /**
     * 灯塔数组
     */
    private Tower[][] towers;

    /**
     * 事件监听者
     */
    private List<AOIEventListener> listeners = new ArrayList<>();

    /**
     * 创建一个AOI对象
     *
     * @param width       地图宽度
     * @param height      地图高度
     * @param towerWidth  每个灯塔的宽度
     * @param towerHeight 每个灯塔的高度
     * @param rangeLimit  视野范围最大值
     */
    public TowerAOI(float width, float height, int towerWidth, int towerHeight, int rangeLimit, int orginX, int orginY) {
        this.width = width;
        this.height = height;
        this.towerWidth = towerWidth;
        this.towerHeight = towerHeight;
        this.orginX = orginX;
        this.orginY = orginY;
        this.rangeLimit = rangeLimit;
        init();
    }

    /**
     * 创建一个AOI对象,灯塔默认宽度为6 灯塔默认高度为5，最大视野范围为5
     *
     * @param width  地图宽度
     * @param height 地图高度
     * @param orginX 地图原点x
     * @param orginY 地图原点y
     */
    public TowerAOI(float width, float height, int orginX, int orginY) {
        this.width = width;
        this.height = height;
        this.orginX = orginX;
        this.orginY = orginY;
        this.towerWidth = WIDTH;
        this.towerHeight = HEIGHT;
        this.rangeLimit = RANGE_LIMIT;
        init();
    }

    /**
     * 初始化地图中的灯塔
     */
    private void init() {
        this.maxX = (int) Math.ceil(this.width / this.towerWidth);
        this.maxY = (int) Math.ceil(this.height / this.towerHeight);

        towers = new Tower[maxX + 1][this.maxY + 1];
        for (int i = 0; i <= this.maxX; i++) {
            for (int j = 0; j <= this.maxY; j++) {
                towers[i][j] = new Tower();
                towers[i][j].setX(i);
                towers[i][j].setY(j);
            }
        }
    }

    /**
     * 将地图坐标转换成灯塔坐标
     *
     * @param vector2
     * @return
     */
    public PointAoi transPos(Vector2f vector2) {
        float rx = Math.abs(vector2.getX() - orginX);
        float ry = Math.abs(vector2.getY() - orginY);
        int px = (int) (rx / towerWidth);
        int py = (int) (ry / towerHeight);
        //转换aoi坐标。数组下标为0开始。所以格子-1
        return new PointAoi(px, py);
    }

    /**
     * 获取Pos周围range大小灯塔中所有指定类型的对象ID
     *
     * @param pos
     * @param range
     * @param type
     * @return
     */
    public List<MapObject> getObjectListByRangeAndType(Vector2f pos, int range, int type) {

        List<MapObject> ret = new ArrayList<>();

        PointAoi tp = transPos(pos);
        int round = 2 * range + 1;
        int num = round * round;
        for (int i = 0; i < num; i++) {
            int x = tp.getX() + i % round - range;
            int y = tp.getY() + i / round - range;
            if (x < 0 || y < 0 || x > this.maxX || y > this.maxY) {
                continue;
            }
            Tower tower = towers[x][y];
            ret.addAll(tower.getObjectByType(type).values());

        }
        return ret;
    }

    public List<MapObject> getObjectListByRangeAndType(Vector2f pos, int range, List<Integer> types) {
        List<MapObject> ret = new ArrayList<>();

        PointAoi tp = transPos(pos);
        int round = 2 * range + 1;
        int num = round * round;
        for (int i = 0; i < num; i++) {
            int x = tp.getX() + i % round - range;
            int y = tp.getY() + i / round - range;
            if (x < 0 || y < 0 || x > this.maxX || y > this.maxY) {
                continue;
            }
            Tower tower = null;
            try {
                tower = towers[x][y];
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int type : types) {
                ret.addAll(tower.getObjectByType(type).values());
            }
        }
        return ret;
    }

    /**
     * 添加一个对象，将对象放入坐标对应的灯塔里面，向灯塔中的观察者触发一个 新对象添加事件
     *
     * @param obj
     * @param v2
     * @return
     */
    public boolean addObject(MapObject obj, Vector2f v2) {
        PointAoi tp = this.transPos(v2);
        this.towers[tp.getX()][tp.getY()].addObject(obj);
        fireAddObjectEvent(obj, this.towers[tp.getX()][tp.getY()].getWatchers());
        return true;
    }

    /**
     * 添加事件观察者
     */
    public boolean addWatcher(MapObject obj, Vector2f v2) {
        int range = RANGE_DEFAULT;
        PointAoi tp = this.transPos(v2);
        PosLimit limit = getPosLimit(tp, range);
        for (int i = limit.getStartX(); i <= limit.getEndX(); i++) {
            for (int j = limit.getStartY(); j <= limit.getEndY(); j++) {
                Tower tower = towers[i][j];
                tower.addWatcher(obj);
            }
        }
        return true;
    }

    public boolean updateObject(MapObject obj, Vector2f newV2){
        Vector2f oldV2 = obj.getVector3().toVector2f();
        PointAoi oldTp = transPos(oldV2);
        PointAoi newTp = transPos(newV2);
        if(oldTp.equals(newTp)){
            //在同一个灯塔的监视范围下
            return true;
        }
        Tower oldTower = towers[oldTp.getX()][oldTp.getY()];
        Tower newTower = towers[newTp.getX()][newTp.getY()];

        Map<Long, MapObject> oldWatchers = oldTower.getWatchers();
        Map<Long, MapObject> newWatchers = newTower.getWatchers();

        Map<Long, MapObject> updatePosWatchers = new HashMap<>();
        Map<Long, MapObject> addPosWatchers = new HashMap<>();
        Map<Long, MapObject> removePosWatchers = new HashMap<>();

        for (Long id: oldWatchers.keySet()) {
            //排除自己
            if(id == obj.getId()){
                continue;
            }
            if (!newWatchers.containsKey(id)) {
                // 移除视野
                removePosWatchers.put(id, oldWatchers.get(id));
            } else {
                // 更新视野
                updatePosWatchers.put(id, oldWatchers.get(id));

            }
        }

        for (Long id : newWatchers.keySet()) {
            if (!oldWatchers.containsKey(id)) {// 通知加入视野
                addPosWatchers.put(id, newWatchers.get(id));
            }
        }

        oldTower.removeObject(obj);
        newTower.addObject(obj);

        fireUpdateObjectEvent(obj, removePosWatchers, updatePosWatchers, addPosWatchers);

        return true;
    }

    /**
     * 更新观测者（自己的视野）
     */
    public boolean updateWatcher(MapObject object, Vector3f newV3){
        int newRange = RANGE_DEFAULT;
        Vector2f newV2 = newV3.toVector2f();
        PointAoi oldTp = transPos(object.getVector3().toVector2f());
        PointAoi newTp = transPos(newV2);

        object.setVector3(newV3);
        if (newTp.getX() == oldTp.getX() && newTp.getY() == oldTp.getY()) {
            // System.out.println("在同一个灯塔范围内无需更新");
            return true;
        }
        TowerChange tc = getChangeTowers(oldTp, newTp, RANGE_DEFAULT, newRange);

        List<MapObject> addObject = new ArrayList<>();
        List<MapObject> removeObject = new ArrayList<>();
        for(Tower tower : tc.getAddTowers()){
            tower.addWatcher(object);
            addObject.addAll(tower.getAllObject().values());
        }

        for(Tower tower : tc.getRemoveTowers()){
            tower.removeWatcher(object);
            removeObject.addAll(tower.getAllObject().values());
        }

        fireUpdateWatcherEvent(object, removeObject, addObject);

        return true;
    }

    /**
     * 触发对象更新事件（发给观察者）
     * @param obj
     * @param watchers
     */
    private void fireAddObjectEvent(MapObject obj, Map<Long, MapObject> watchers){
        if(watchers.size() == 0){
            return;
        }
        for(AOIEventListener listener : listeners){
            listener.onAdd(obj, watchers);
        }
    }

    /**
     * 触发对象更新事件（发给观察者）
     */
    private void fireUpdateObjectEvent(MapObject obj, Map<Long, MapObject> removeWatchers, Map<Long, MapObject> updateWatchers,
                                       Map<Long, MapObject> newWatchers){
        if(removeWatchers.size() == 0 && updateWatchers.size() == 0 && newWatchers.size() == 0){
            return;
        }
        for(AOIEventListener listener : listeners){
            listener.onUpdate(obj, removeWatchers, updateWatchers, newWatchers);
        }
    }

    private void fireUpdateWatcherEvent(MapObject obj, List<MapObject> removeObj, List<MapObject> addObj){
        if(removeObj.size() == 0 && addObj.size() == 0){
            return;
        }
        for (AOIEventListener listener: listeners) {
            listener.onUpdateWatcher(obj, removeObj, addObj);
        }
    }


    public TowerChange getChangeTowers(PointAoi oldTp, PointAoi newTp, int oldRange, int newRange){
        List<Tower> removeTowers = new ArrayList<>();
        List<Tower> addTowers =  new ArrayList<>();
        List<Tower> unChangeTowers = new ArrayList<>();

        PosLimit oldLimit = getPosLimit(oldTp, oldRange);
        PosLimit newLimit = getPosLimit(newTp, newRange);

        for (int x = oldLimit.getStartX(); x <= oldLimit.getEndX(); x++) {
            for (int y = oldLimit.getStartY(); y <= oldLimit.getEndY(); y++) {
                if (isInRect(x, y, newLimit)) {
                    // 不变的Tower
                    unChangeTowers.add(towers[x][y]);
                } else {
                    // 需要移除的Tower
                    removeTowers.add(towers[x][y]);
                }
            }
        }
        // 计算新增的Tower
        for (int x = newLimit.getStartX(); x <= newLimit.getEndX(); x++) {
            for (int y = newLimit.getStartY(); y <= newLimit.getEndY(); y++) {
                if (!isInRect(x, y, oldLimit)) {
                    addTowers.add(towers[x][y]);
                }
            }
        }

        return new TowerChange(removeTowers, addTowers, unChangeTowers);
    }

    /**
     * 是否在矩形之中
     *
     * @param x
     * @param y
     * @param limit
     * @return
     */
    private boolean isInRect(int x, int y, PosLimit limit) {
        return x >= limit.getStartX() && x <= limit.getEndX() && y >= limit.getStartY() && y <= limit.getEndY();
    }

    /**
     * 获取指定范围的格子限制坐标
     *
     * @param pos
     * @param range
     * @return
     */
    private PosLimit getPosLimit(PointAoi pos, int range) {

        int startX;
        int startY;

        int endX;
        int endY;

        if (pos.getX() - range < 0) {
            startX = 0;
            endX = 2 * range - 1;
        } else if (pos.getX() + range > this.maxX) {
            endX = this.maxX;
            startX = this.maxX - 2 * range + 1;
        } else {
            startX = pos.getX() - range;
            endX = pos.getX() + range;
        }

        if (pos.getY() - range < 0) {
            startY = 0;
            endY = range * 2 - 1;
        } else if (pos.getY() + range > this.maxY) {
            endY = this.maxY;
            startY = this.maxY - range * 2 + 1;
        } else {
            startY = pos.getY() - range;
            endY = pos.getY() + range;
        }

        startX = startX >= 0 ? startX : 0;
        endX = endX < this.maxX ? endX : this.maxX;
        startY = startY >= 0 ? startY : 0;
        endY = endY < this.maxY ? endY : this.maxY;
        return new PosLimit(startX, endX, startY, endY);
    }

    public void addListener(AOIEventListener listener) {
        this.listeners.add(listener);
    }
}
