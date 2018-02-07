package io.github.robhinds.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.robhinds.box2d.RunnerUserData;
import io.github.robhinds.utils.AudioUtils;
import io.github.robhinds.utils.Constants;

public class Runner extends GameActor {

    private Animation runningAnimation;
    private Animation hitRunningAnimation;
    private TextureRegion jumpingTexture;

    private float stateTime;
    private boolean hit = false;
    private boolean dodging = false;
    private boolean jumping = false;
    private boolean doubleJumping = false;
    private boolean invincible = false;
    private Sound jumpSound;
    private Sound hitSound;

    public Runner(Body body) {
        super(body);
        TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Constants.RUNNER_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.RUNNER_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.RUNNER_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        TextureRegion[] hitRunningFrames = new TextureRegion[Constants.RUNNER_RUNNING_REGION_NAMES.length*2];
        for (int i = 0; i < Constants.RUNNER_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.RUNNER_RUNNING_REGION_NAMES[i];
            hitRunningFrames[i*2] = textureAtlas.findRegion(path);
            hitRunningFrames[i*2+1] = new TextureRegion(new Texture(Gdx.files.internal(Constants.TRANSPARENT_IMAGE_PATH)));
        }
        runningAnimation = new Animation(0.1f, runningFrames);
        hitRunningAnimation = new Animation(0.1f, hitRunningFrames);
        jumpingTexture = new TextureRegion(new Texture(Gdx.files.internal(Constants.JUMPING_IMAGE_PATH)));
        stateTime = 0f;
        jumpSound = AudioUtils.getInstance().getJumpSound();
        hitSound = AudioUtils.getInstance().getHitSound();
    }

    @Override public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    @Override public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (jumping) {
            batch.draw(jumpingTexture, screenRectangle.x, screenRectangle.y, screenRectangle.width,
                    screenRectangle.height);
        } else {
            // Running
            stateTime += Gdx.graphics.getDeltaTime();
            if (isHit()) {
                batch.draw(hitRunningAnimation.getKeyFrame(stateTime, true), screenRectangle.x, screenRectangle.y,
                        screenRectangle.getWidth(), screenRectangle.getHeight());
            } else {
                batch.draw(runningAnimation.getKeyFrame(stateTime, true), screenRectangle.x, screenRectangle.y,
                        screenRectangle.getWidth(), screenRectangle.getHeight());
            }
        }
    }

    public void jump() {
        if (!doubleJumping) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            doubleJumping = jumping;
            AudioUtils.getInstance().playSound(jumpSound);
            jumping = true;
        }
    }

    public void landed() {
        if (isHorizontal()) {
            //standUpRunner();
            System.out.println("CURRENT ORIENTATION = " + body.getTransform().getRotation());
        }
        jumping = false;
        doubleJumping = false;
    }

    public void hit() {
        if (!invincible) {
            body.applyLinearImpulse(getUserData().getHitAngularImpulse(), body.getWorldCenter(), true);
            hit = true;
            AudioUtils.getInstance().playSound(hitSound);
            invincible = true;
        }
    }

    public void dodge() {
        if (!jumping) {
            body.setTransform(body.getPosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;
        body.setTransform(new Vector2(body.getPosition().x, Constants.RUNNER_Y - Constants.RUNNER_HEIGHT), 0f);
    }

    public boolean isDodging() {
        return dodging;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean h) {
        hit = h;
    }

    public boolean isHorizontal() {
        return body.getAngle() != 0;
    }

    public boolean isInvincible() { return invincible; }

    public void setInvincible(boolean i) { this.invincible = i; }
}