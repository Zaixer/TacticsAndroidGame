package com.prototype.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.prototype.game.PrototypeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Prototype Game";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new PrototypeGame(), config);
	}
}
