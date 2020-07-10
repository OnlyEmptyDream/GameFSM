package com.kong.aoi.obj;

import com.kong.common.constant.Dir;
import com.kong.common.obj.MapScene;
import com.kong.path.GeomUtil;
import lombok.Data;

import java.awt.*;
import java.util.Objects;

@Data
public class Vector2f {
    int x;
    int y;



    //寻路相关是否被阻挡
    boolean block;

    // A*寻路相关-总消耗
    private int f;

    //寻路相关 到目标点 预计还需要的距离量
    private int h;

    //寻路相关 当前点距离开始点的距离量
    private int g;

    //寻路相关 父节点
    private Vector2f parent;

    //子节点
    private Vector2f son;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return x == vector2f.x &&
                y == vector2f.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2f(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void clearRouting(){
        parent = null;
        son = null;
        g = 0;
        h = 0;
        f = 0;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    private Vector2f[] nears;

}
