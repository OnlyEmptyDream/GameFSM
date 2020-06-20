package com.kong.aoi.obj;

import lombok.Data;

import java.util.Objects;

@Data
public class Vector3f {
    int x;
    int y;
    int z;

    public Vector3f(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3f vector3f = (Vector3f) o;
        return x == vector3f.x &&
                y == vector3f.y &&
                z == vector3f.z;
    }

    public Vector3f addX(int count){
        x = x + count < 0 ? x : x + count;
        return this;
    }

    public Vector3f addY(int count){
        y = y + count < 0 ? y : y + count;
        return this;
    }


    public Vector3f addZ(int count){
        z = z + count < 0 ? z : z + count;
        return this;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public Vector2f toVector2f(){
        return new Vector2f(this.getX(), this.getY());
    }
}
