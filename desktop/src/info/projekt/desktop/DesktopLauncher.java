package info.projekt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.projekt.christoph.Screen;
import info.projekt.christoph.TitleScreen;
import info.projekt.jonas.storage.StorageHandler;

import java.awt.*;
import java.io.IOException;

public class DesktopLauncher {
    public static void main(String[] arg) throws IOException {
        Screen TitleScreen = new Screen();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
        config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
        config.fullscreen = false;
        config.vSyncEnabled = false;
        config.foregroundFPS = 60;
        config.backgroundFPS = 60;
        config.forceExit = true;
        final LwjglApplication lwjglApplication = new LwjglApplication(TitleScreen, config);
        new TitleScreen(TitleScreen);
        //StorageHandler.registerArmors();
        StorageHandler.registerWeapons();
    }
}
