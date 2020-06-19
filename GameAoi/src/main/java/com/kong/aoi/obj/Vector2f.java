package com.kong.aoi.obj;

import lombok.Data;

import java.util.Objects;

@Data
public class Vector2f {
    int x;
    int y;

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
}
