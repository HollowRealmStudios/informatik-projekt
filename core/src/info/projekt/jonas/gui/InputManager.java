package info.projekt.jonas.gui;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;

public class InputManager extends InputMultiplexer {


    private HashMap<InputProcessor, Integer> processors;

    public void suspendProcessor(InputProcessor processor, int clicks) {
        processors.put(processor, clicks);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        processors.forEach((proc, integer) -> {
            if(integer > 0) {
                integer--;
                removeProcessor(proc);
            }
            else {
                processors.remove(proc);
                addProcessor(proc);
            }
        });
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
