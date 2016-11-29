package io.github.robhinds;

import com.badlogic.gdx.Game;

import io.github.robhinds.screens.GameScreen;

public class MonsterDashGame extends Game {

    @Override public void create () {
        setScreen(new GameScreen());
    }

}
