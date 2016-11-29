package io.github.robhinds.box2d;

import io.github.robhinds.enums.UserDataType;

public abstract class UserData {

    protected UserDataType userDataType;

    public UserData() { }

    public UserDataType getUserDataType() {
        return userDataType;
    }
}