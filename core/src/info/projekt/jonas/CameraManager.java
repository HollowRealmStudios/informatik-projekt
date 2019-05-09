package info.projekt.jonas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Jonas
 * Simple wrapper for an orthographic camera
 */
public class CameraManager {

    private final OrthographicCamera camera;

    public CameraManager() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void setCameraSize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        update();
    }

    /**
     * updates the camera. Call after updating any parameters
     */
    private void update() {
        camera.update();
    }

    /**
     * @return returns the combined matrix of the camera
     */
    public Matrix4 getMatrix() {
        return camera.combined;
    }

    /**
     * sets a new zoom for the camera
     *
     * @param zoom the new zoom
     */
    public void setZoom(float zoom) {
        camera.zoom = zoom;
        update();
    }

    /**
     * translate the camera relative to its current position
     *
     * @param vector the vector to translate, given in x and y coordinates
     */
    public void translateRelative(Vector2 vector) {
        camera.translate(vector);
        update();
    }


    /**
     * translate the camera absolute to its current position
     *
     * @param vector the vector to translate, given in x an y coordinates
     */
    public void translateAbsolute(Vector2 vector) {
        camera.position.set(vector, 0f);
        update();
    }

    @Deprecated
    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public String toString() {
        return camera.position.toString() + ", zoom: " + camera.zoom;
    }
}
