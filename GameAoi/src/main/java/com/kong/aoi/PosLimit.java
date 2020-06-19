package com.kong.aoi;

import lombok.Data;

@Data
public class PosLimit {
    public PosLimit(int startX, int endX, int startY, int endY) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    private int startX;

    private int endX;

    private int startY;

    private int endY;
}
