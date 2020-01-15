package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.FlappyProject;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyProject.WIDTH;
		config.height = FlappyProject.HEIGHT;
		config.title = FlappyProject.TITLE;
		new LwjglApplication(new FlappyProject(), config);
	}
}
