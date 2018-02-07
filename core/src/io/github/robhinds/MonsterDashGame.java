package io.github.robhinds;

import com.badlogic.gdx.Game;

import io.github.robhinds.screens.GameScreen;
import io.github.robhinds.utils.AudioUtils;

public class MonsterDashGame extends Game {

    @Override public void create () {
        setScreen(new GameScreen());
    }

    @Override public void dispose() {
        super.dispose();
        AudioUtils.dispose();
    }

}
