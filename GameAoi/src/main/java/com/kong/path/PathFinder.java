package com.kong.path;

import com.kong.aoi.obj.Vector2f;

import java.util.*;

public class PathFinder {
    private OpenPointBinaryHeap openList;
    private Set<Vector2f> closeSet;
    private Set<Vector2f> openSet;

    public PathFinder(int width, int height) {
        this.openList = new OpenPointBinaryHeap(width * height);
        this.closeSet = new HashSet<>();
        this.openSet = new HashSet<>();
    }

//    public List<Vector2f> route(Vector2f start, Vector2f end) {
//        return route(start, end, 10000000);
//    }

    public List<Vector2f> route(Vector2f start, Vector2f end, int limit){
        closeSet.clear();
        openSet.clear();
        openList.clear();
        if(start.isBlock()){
            return Collections.emptyList();
        }
        start.clearRouting();
        openList.push(start);

        start.clearRouting();
        Vector2f currentPoint;
        boolean limited = false;

        //开始寻路
        do{
            if (openList.isEmpty()){
                // 未找到终点，且可走的路径列表为空
                return Collections.emptyList();
            }

            currentPoint = openList.pop();

            if(currentPoint.getG() > limit * 10){
                //超过深度限制
                limited = true;
                break;
            }

            closeSet.add(currentPoint);
            Vector2f[] nears = currentPoint.getNears();

            for (int index = 0; index < nears.length; index++){
                Vector2f pos = nears[index];
                if (pos == null){
                    continue;
                }

                if(pos != end && pos.isBlock()){
                    continue;
                }
                if(closeSet.contains(pos)){
                    //已关闭
                    continue;
                }
                if(!openSet.contains(pos)){
                    //未开启
                    pos.setParent(currentPoint); //下一步可以走的位置

                    pos.setG(getG(currentPoint, index));
                    pos.setH(getH(pos, end) * 10);
                    pos.setF(pos.getG()+ pos.getH());
                    openList.push(pos);
                    openSet.add(pos);
                }else{
                    //节点已经开启 但不同的走法因此G会发生改变
                    int oldF = pos.getF();
                    int newG = getG(currentPoint, index);
                    int newF = newG + pos.getH();
                    if(newF < oldF){
                        //跟新g值 和父节点
                        pos.setParent(currentPoint);
                        pos.setG(newG);
                        pos.setF(newF);
                        openList.update(pos);//更新二叉堆
                    }
                }
            }
        }while (!closeSet.contains(end));
        LinkedList<Vector2f> ret = new LinkedList<>();
        Vector2f last = end;
        if(limited){
            last = currentPoint;
        }
        //返回的寻路列表是反向的  即是从目标点 到 初始点， 因为用首部插入新元素
        ret.contains(end);
        while(last.getParent() != null && last != start){
            ret.addFirst(last);
            last = last.getParent();
        }
        return ret;
    }

    /**
     * 获得某个坐标的G值，从起始点走到该节点的代价，走一个直线节点10的代价，走一个对角线（直线）14的代价
     *
     * @param parent
     * @param index
     * @return
     */
    private static int getG(Vector2f parent, int index) {
        int parentG = parent.getG();
        // 方向是奇数表示斜线，偶数表示直线
        if (index % 2 == 0) {
            return parentG + 10;
        } else {
            return parentG + 14;
        }

    }

    /**
     * 获得某个坐标的H值，采用曼哈顿距离，从该节点到终点的代价
     * 说的很牛逼 其实也就是虚拟绝对距离
     *
     * @param pos
     * @return
     */
    private static int getH(Vector2f pos, Vector2f end) {
        return Math.abs(pos.getX() - end.getX()) + Math.abs(pos.getY() - end.getY());
    }
}
