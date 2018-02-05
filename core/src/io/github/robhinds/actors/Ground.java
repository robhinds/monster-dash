package io.github.robhinds.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import io.github.robhinds.box2d.GroundUserData;
import io.github.robhinds.utils.Constants;

public class Ground extends GameActor {

    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;

    public Ground(Body body) {
        super(body);

        Texture imgTexture = new Texture(Gdx.files.internal("scribble.png"));
        imgTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        textureRegion = new TextureRegion(imgTexture);
        textureRegion.setRegion(0,0,imgTexture.getWidth()*3,imgTexture.getHeight()*3);

        textureRegionBounds1 = new Rectangle(0 - getUserData().getWidth() / 2, 0, getUserData().getWidth(),
                getUserData().getHeight());
        textureRegionBounds2 = new Rectangle(getUserData().getWidth() / 2, 0, getUserData().getWidth(),
                getUserData().getHeight());
    }

    @Override public GroundUserData getUserData() {
        return (GroundUserData) userData;
    }

    @Override public void act(float delta) {
        super.act(delta);
        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }

    @Override public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, screenRectangle.y, screenRectangle.getWidth(),
                screenRectangle.getHeight());
        batch.draw(textureRegion, textureRegionBounds2.x, screenRectangle.y, screenRectangle.getWidth(),
                screenRectangle.getHeight());
    }

    private boolean leftBoundsReached(float delta) {
        return (textureRegionBounds2.x - transformToScreen(delta * Constants.GROUND_SPEED)) <= 0;
    }

    private void updateXBounds(float delta) {
        textureRegionBounds1.x += transformToScreen(delta * Constants.GROUND_SPEED);
        textureRegionBounds2.x += transformToScreen(delta * Constants.GROUND_SPEED);
    }

    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(textureRegionBounds1.x + screenRectangle.width, 0, screenRectangle.width,
                screenRectangle.height);
    }

}