package info.projekt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.projekt.InfoProjekt;
import info.projekt.christoph.TitleScreen;
import info.projekt.jonas.gui.RenderUtils;
import info.projekt.jonas.storage.StorageHandler;

import java.awt.*;
import java.io.IOException;

public class DesktopLauncher {

	public static void main(String[] arg) throws IOException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = RenderUtils.WIDTH;
		config.height = RenderUtils.HEIGHT;
		config.fullscreen = true;
		config.vSyncEnabled = false;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.forceExit = true;
		final LwjglApplication lwjglApplication = new LwjglApplication(new InfoProjekt(), config);
		StorageHandler.registerArmors();
		StorageHandler.registerWeapons();
	}
}
