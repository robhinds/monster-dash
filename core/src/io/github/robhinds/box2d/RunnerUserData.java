package io.github.robhinds.box2d;

import com.badlogic.gdx.math.Vector2;

import io.github.robhinds.enums.UserDataType;
import io.github.robhinds.utils.Constants;

public class RunnerUserData extends UserData {

    public RunnerUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
    }

    public float getHitAngularImpulse() {
        return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
    }

    public Vector2 getRunBackLinearImpulse() {
        return Constants.RUNNER_RUN_BACK_LINEAR_IMPULSE;
    }
}
