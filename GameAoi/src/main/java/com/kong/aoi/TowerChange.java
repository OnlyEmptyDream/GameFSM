package com.kong.aoi;

import java.util.List;

public class TowerChange {
    /**
     * 这个类 不是对灯塔的操作  而是玩家在移动时 需要更新的灯塔状态
     * 如 原先玩家被1，2，3灯塔观察 现在为2，3，4 那么2，3为unChangeTowers(调用更新api), 1为removeTowers（调用remove）， 4为addTowers调用（add）
     */

    private List<Tower> removeTowers;

    private List<Tower> addTowers;

    private List<Tower> unChangeTowers;

    public TowerChange(List<Tower> removeTowers, List<Tower> addTowers, List<Tower> unChangeTowers) {
        this.removeTowers = removeTowers;
        this.addTowers = addTowers;
        this.unChangeTowers = unChangeTowers;
    }

    public List<Tower> getRemoveTowers() {
        return removeTowers;
    }

    public void setRemoveTowers(List<Tower> removeTowers) {
        this.removeTowers = removeTowers;
    }

    public List<Tower> getAddTowers() {
        return addTowers;
    }

    public void setAddTowers(List<Tower> addTowers) {
        this.addTowers = addTowers;
    }

    public List<Tower> getUnChangeTowers() {
        return unChangeTowers;
    }

    public void setUnChangeTowers(List<Tower> unChangeTowers) {
        this.unChangeTowers = unChangeTowers;
    }

}
