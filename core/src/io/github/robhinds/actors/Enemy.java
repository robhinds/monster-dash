package io.github.robhinds.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.robhinds.box2d.EnemyUserData;
import io.github.robhinds.utils.Constants;

public class Enemy extends GameActor {

    private final TextureRegion textureRegion;
    public Enemy(Body body) {
        super(body);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.ENEMY_IMAGE_PATH)));
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
        batch.draw(textureRegion, screenRectangle.x, screenRectangle.y,
                screenRectangle.getWidth(), screenRectangle.getHeight());
    }

}
