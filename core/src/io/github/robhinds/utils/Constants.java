package io.github.robhinds.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;
    public static final float WORLD_TO_SCREEN = 32;

    // This will be our viewport measurements while working with the debug renderer
    public static final int VIEWPORT_WIDTH = 20;
    public static final int VIEWPORT_HEIGHT = 13;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 2 * VIEWPORT_WIDTH;
    public static final float GROUND_HEIGHT = 0.5f * VIEWPORT_HEIGHT;
    public static final float GROUND_DENSITY = 0f;

    public static final float RUNNER_X = VIEWPORT_WIDTH/2;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 1f;
    public static final float RUNNER_HEIGHT = 2f;
    public static final float RUNNER_GRAVITY_SCALE = 3f;
    public static final String BACKGROUND_IMAGE_PATH = "background.png";
    public static final String GROUND_IMAGE_PATH = "ground.png";
    public static final String BACKGROUND_CLOUD_PATH = "clouds.png";
    public static float RUNNER_DENSITY = 0.5f;
    public static final float RUNNER_DODGE_X = 2f;
    public static final float RUNNER_DODGE_Y = 1.5f;
    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 13f);
    public static final float RUNNER_HIT_ANGULAR_IMPULSE = 10f;
    public static Vector2 RUNNER_RUN_BACK_LINEAR_IMPULSE = new Vector2(4f, 0);

    public static final float ENEMY_X = VIEWPORT_WIDTH + 5f;
    public static final float ENEMY_Y = GROUND_HEIGHT/2;
    public static final float ENEMY_DENSITY = RUNNER_DENSITY;
    public static final float FLYING_ENEMY_Y = 5f;
    public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-6f, 0);

    public static final float CLOUD_X = VIEWPORT_WIDTH + 5f;
    public static final float CLOUD_Y = VIEWPORT_HEIGHT;
    public static final float CLOUD_DENSITY = -10;
    public static final Vector2 CLOUD_LINEAR_VELOCITY = new Vector2(-2f, 0);
}
