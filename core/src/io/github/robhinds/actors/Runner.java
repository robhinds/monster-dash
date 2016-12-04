package io.github.robhinds.actors;

import com.badlogic.gdx.physics.box2d.Body;

import io.github.robhinds.box2d.RunnerUserData;

public class Runner extends GameActor {

    private boolean hit = false;
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

    public boolean isHit() {
        return hit;
    }

    public boolean isHorizontal() {
        return body.getAngle() != 0;
    }
}