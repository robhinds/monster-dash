package io.github.robhinds.enums;

import io.github.robhinds.utils.Constants;

public enum CloudType {

    SMALL(1f, 1f, Constants.CLOUD_X, Constants.CLOUD_Y * 0.85f, Constants.CLOUD_DENSITY),
    BIG(2f, 1f, Constants.CLOUD_X, Constants.CLOUD_Y * 0.75f, Constants.CLOUD_DENSITY);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;

    CloudType(float width, float height, float x, float y, float density) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y + height/2;
        this.density = density;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }

}