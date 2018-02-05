package io.github.robhinds.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.robhinds.box2d.EnemyUserData;
import io.github.robhinds.utils.Constants;

public class Enemy extends GameActor {

    private Animation runningAnimation;
    private float stateTime;

    public Enemy(Body body) {
        super(body);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.BADDY_CHARACTERS_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Constants.BADDY_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.BADDY_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.BADDY_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        runningAnimation = new Animation(0.1f, runningFrames);
        stateTime = 0f;
    }

    @Override public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    @Override public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw(runningAnimation.getKeyFrame(stateTime, true), screenRectangle.x, screenRectangle.y,
                screenRectangle.getWidth(), screenRectangle.getHeight());
    }

}
