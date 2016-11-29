package io.github.robhinds.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.robhinds.MonsterDashGame;
import io.github.robhinds.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = Constants.APP_WIDTH;
        config.height = Constants.APP_HEIGHT;
		new LwjglApplication(new MonsterDashGame(), config);
	}
}
