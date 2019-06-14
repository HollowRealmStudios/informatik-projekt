package info.projekt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.projekt.InfoProjekt;
import info.projekt.jonas.rooms.Room;
import org.reflections.Reflections;

import java.awt.*;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
		config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		config.fullscreen = true;
		config.vSyncEnabled = false;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.forceExit = true;
		new LwjglApplication(new InfoProjekt(), config);
		//FIXME
	}
}
