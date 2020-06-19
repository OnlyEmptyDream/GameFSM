package com.kong.aoi;

import lombok.Data;

@Data
public class PointAoi {
    private int x;

    private int y;

    public PointAoi(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
