package io.github.robhinds.enums;

import io.github.robhinds.utils.Constants;

public enum EnemyType {

    RUNNING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.ENEMY_Y, Constants.ENEMY_DENSITY),
    RUNNING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.ENEMY_Y, Constants.ENEMY_DENSITY),
    RUNNING_LONG(1f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y, Constants.ENEMY_DENSITY),
    RUNNING_BIG(2f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y, Constants.ENEMY_DENSITY);
    //FLYING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY),
    //FLYING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;

    EnemyType(float width, float height, float x, float y, float density) {
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