package com.kong.aoi;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Data
public class TowerAOI {
    private static final Logger LOGGER = LoggerFactory.getLogger(TowerAOI.class);

    public static final int WIDTH = 5000;
    public static final int HEIGHT = 2000;
    public static final int RANGE_LIMIT = 5;

    public static final int RANGE_DEFAULT = 2;

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
     * 获取Pos周围range大小灯塔中所有指定类型的对象ID
     *
     * @param pos
     * @param range
     * @param type
     * @return
     */
//    public List<IMapObject> getObjectListByRangeAndType(Vector2f pos, int range, int type) {
////        if (range < 0 || range > this.rangeLimit || !checkPos(pos)) {
////            return Collections.emptyList();
////        }
//
//        List<IMapObject> ret = new ArrayList<>();
//
//        PointAoi tp = transPos(pos);
//        int round = 2 * range + 1;
//        int num = round * round;
//        for (int i = 0; i < num; i++) {
//            int x = tp.getX() + i % round - range;
//            int y = tp.getY() + i / round - range;
//            if (x < 0 || y < 0 || x > this.maxX || y > this.maxY) {
//                continue;
//            }
//            Tower tower = towers[x][y];
//            ret.addAll(tower.getObjectByType(type).values());
//
//        }
//        return ret;
//    }
//
//    public List<IMapObject> getObjectListByRangeAndType(Vector2f pos, int range, List<Integer> types) {
//        if (range < 0 || range > this.rangeLimit || !checkPos(pos)) {
//            return Collections.emptyList();
//        }
//
//        List<IMapObject> ret = new ArrayList<>();
//
//        PointAoi tp = transPos(pos);
//        int round = 2 * range + 1;
//        int num = round * round;
//        for (int i = 0; i < num; i++) {
//            int x = tp.getX() + i % round - range;
//            int y = tp.getY() + i / round - range;
//            if (x < 0 || y < 0 || x > this.maxX || y > this.maxY) {
//                continue;
//            }
//            Tower tower = null;
//            try {
//                tower = towers[x][y];
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            for (int type : types) {
//                ret.addAll(tower.getObjectByType(type).values());
//            }
//        }
//        return ret;
//    }


}
