package io.github.robhinds.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.github.robhinds.utils.Constants;

public class Cloud extends Actor {

    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private int speed = 20;

    public Cloud() {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_CLOUD_PATH)));
        textureRegionBounds1 = new Rectangle(0 - Constants.APP_WIDTH / 2, Constants.APP_HEIGHT/2, Constants.APP_WIDTH, Constants.APP_HEIGHT/2);
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, Constants.APP_HEIGHT/2, Constants.APP_WIDTH, Constants.APP_HEIGHT/2);
    }

    @Override  public void act(float delta) {
        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }

    @Override public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT/2);
        batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT/2);
    }

    private boolean leftBoundsReached(float delta) {
        return (textureRegionBounds2.x - (delta * speed)) <= 0;
    }

    private void updateXBounds(float delta) {
        textureRegionBounds1.x += delta * speed;
        textureRegionBounds2.x += delta * speed;
    }

    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH, Constants.APP_HEIGHT/2, Constants.APP_WIDTH, Constants.APP_HEIGHT/2);
    }
}
