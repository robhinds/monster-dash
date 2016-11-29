package io.github.robhinds.box2d;

import io.github.robhinds.enums.UserDataType;

public class GroundUserData extends UserData {
    public GroundUserData() {
        super();
        userDataType = UserDataType.GROUND;
    }
}
