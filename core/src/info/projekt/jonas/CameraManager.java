package info.projekt.jonas;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Jonas
 * Simple wrapper for an orthographic camera
 */
public class CameraManager {

    private final OrthographicCamera camera;

    /**
     * @param width  the width of the camera's field of view
     * @param height the height of the camera's field of view
     * @param zoom   the zoom of the camera, 1 is default
     */
    public CameraManager(int width, int height, int zoom) {
        camera = new OrthographicCamera(width, height);
        camera.zoom = zoom;
    }

    /**
     * updates the camera. Call after updating any parameters
     */
    public void update() {
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
    }

    /**
     * translate the camera relative to its current position
     *
     * @param vector the vector to translate, given in x and y coordinates
     */
    public void translateRelative(Vector2 vector) {
        camera.translate(vector);
    }


    /**
     * translate the camera absolute to its current position
     *
     * @param vector the vector to translate, given in x an y coordinates
     */
    public void translateAbsolute(Vector2 vector) {
        camera.position.set(vector, 0f);
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
