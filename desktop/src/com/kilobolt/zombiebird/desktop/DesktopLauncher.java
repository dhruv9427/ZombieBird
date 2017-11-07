package com.kilobolt.zombiebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kilobolt.zombiebird.ZBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//Initial setup of the splash screen
		//Call image badlogic.jpg
		//Comments
		config.title="Zombie Bird";
		config.width=272;
		config.height=408;
		new LwjglApplication(new ZBGame(), config);
	}
}
