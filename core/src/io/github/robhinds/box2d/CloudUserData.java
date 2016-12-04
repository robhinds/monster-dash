package io.github.robhinds.box2d;

import com.badlogic.gdx.math.Vector2;

import io.github.robhinds.enums.UserDataType;
import io.github.robhinds.utils.Constants;

public class CloudUserData extends UserData {

    private Vector2 linearVelocity;

    public CloudUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.CLOUD;
        linearVelocity = Constants.CLOUD_LINEAR_VELOCITY;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

}
