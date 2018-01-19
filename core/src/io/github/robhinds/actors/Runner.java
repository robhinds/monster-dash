package io.github.robhinds.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.robhinds.box2d.RunnerUserData;
import io.github.robhinds.utils.Constants;

public class Runner extends GameActor {

    private boolean hit = false;
    private boolean dodging = false;
    private boolean jumping = false;
    private boolean doubleJumping = false;
    private boolean invincible = false;

    public Runner(Body body) {
        super(body);
    }

    @Override public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    public void jump() {
        if (!doubleJumping && !hit) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            doubleJumping = jumping;
            jumping = true;
        }
    }

    public void landed() {
        if (isHorizontal()) {
            //standUpRunner();
            System.out.println("CURRENT ORIENTATION = " + body.getTransform().getRotation());
        }
        hit = false;
        jumping = false;
        doubleJumping = false;
    }

    public void hit() {
        if (!invincible) {
            body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
            hit = true;
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

    public boolean isHorizontal() {
        return body.getAngle() != 0;
    }
}