package io.github.robhinds.box2d;

import io.github.robhinds.enums.UserDataType;

public class GroundUserData extends UserData {
    public GroundUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.GROUND;
    }
}
