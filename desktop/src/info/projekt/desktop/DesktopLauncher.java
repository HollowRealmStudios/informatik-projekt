package info.projekt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.projekt.InfoProjekt;

import java.awt.*;

public class DesktopLauncher {

    public static void main(String[] arg) {
        new LwjglApplication(new InfoProjekt(), new LwjglApplicationConfiguration() {{
            width = Toolkit.getDefaultToolkit().getScreenSize().width;
            height = Toolkit.getDefaultToolkit().getScreenSize().height;
            fullscreen = true;
            vSyncEnabled = true;
            foregroundFPS = 60;
            backgroundFPS = 60;
            forceExit = true;
        }});
    }
}
