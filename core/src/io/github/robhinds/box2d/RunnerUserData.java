package io.github.robhinds.box2d;

import com.badlogic.gdx.math.Vector2;

import io.github.robhinds.enums.UserDataType;
import io.github.robhinds.utils.Constants;

public class RunnerUserData extends UserData {

    private final Vector2 runningPosition = new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
    private final Vector2 dodgePosition = new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);
    private Vector2 jumpingLinearImpulse;

    public RunnerUserData(float width, float height) {
        super(width, height);
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        userDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public Vector2 getHitAngularImpulse() {
        return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
    }

    public Vector2 getRunBackLinearImpulse() {
        return Constants.RUNNER_RUN_BACK_LINEAR_IMPULSE;
    }

    public float getDodgeAngle() {
        return (float) (-90f * (Math.PI / 180f)); // In radians
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }

    public Vector2 getDodgePosition() {
        return dodgePosition;
    }

}
