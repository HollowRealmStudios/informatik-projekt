package info.projekt.jonas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

public class InputHandler {

    private Timer timer;
    private ArrayList<InputReciever> receivers = new ArrayList<InputReciever>();

    public InputHandler() {
        timer = new Timer();
    }

    public void addReciever(InputReciever receiver) {
        receivers.add(receiver);
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                for (InputReciever reciever : receivers) {
                    if (Gdx.input.isKeyPressed(Input.Keys.W)) reciever.movePressed(Input.Keys.W);
                    else if (Gdx.input.isKeyPressed(Input.Keys.A)) reciever.movePressed(Input.Keys.A);
                    else if (Gdx.input.isKeyPressed(Input.Keys.S)) reciever.movePressed(Input.Keys.S);
                    else if (Gdx.input.isKeyPressed(Input.Keys.D)) reciever.movePressed(Input.Keys.D);
                }
            }
        }, 0f, 0.1f);
    }
}
