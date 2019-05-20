package info.projekt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.projekt.InfoProjekt;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;

import java.awt.*;
import java.io.IOException;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
        config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
        config.fullscreen = true;
        config.vSyncEnabled = true;
        new LwjglApplication(new InfoProjekt(), config);
    }
}
