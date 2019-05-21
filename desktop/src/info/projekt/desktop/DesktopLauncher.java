package info.projekt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.projekt.christoph.Screen;
import info.projekt.christoph.TitleScreen;

import java.awt.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Screen TitleScreen = new Screen();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
        config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
        config.fullscreen = true;
        config.vSyncEnabled = true;
        final LwjglApplication lwjglApplication = new LwjglApplication(TitleScreen, config);
        new TitleScreen(TitleScreen);
    }
}
