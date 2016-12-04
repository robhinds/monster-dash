package io.github.robhinds.actors;

import com.badlogic.gdx.physics.box2d.Body;

import io.github.robhinds.box2d.CloudUserData;
import io.github.robhinds.box2d.EnemyUserData;

public class Cloud extends GameActor {

    public Cloud(Body body) {
        super(body);
    }

    @Override public CloudUserData getUserData() {
        return (CloudUserData) userData;
    }

    @Override public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

}
