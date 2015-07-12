package com.prototype.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.prototype.game.PrototypeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Prototype Game";
		config.width = 640;
		config.height = 360;
		new LwjglApplication(new PrototypeGame(), config);
	}
}
