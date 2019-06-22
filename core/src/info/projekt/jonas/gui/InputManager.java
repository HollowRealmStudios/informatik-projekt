package info.projekt.jonas.gui;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;
import java.util.Map;

public class InputManager extends InputMultiplexer {


    private HashMap<InputProcessor, Integer> processors = new HashMap<>();

    public void suspendProcessor(InputProcessor processor, int clicks) {
        processors.put(processor, clicks);
    }

    public boolean isSuspended(InputProcessor processor) {
    	return processors.containsKey(processor);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (Map.Entry<InputProcessor, Integer> entry : processors.entrySet()) {
            if(entry.getValue() > 0) {
                processors.put(entry.getKey(), entry.getValue() - 1);
                removeProcessor(entry.getKey());
            }
            else {
                processors.remove(entry.getKey());
                addProcessor(entry.getKey());
            }
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
