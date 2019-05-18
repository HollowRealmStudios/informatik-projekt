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
		camera.position.set(new Vector2(1000, 1000), 0);
		update();
	}

	/**
	 * Sets the camera's viewport size
	 *
	 * @param width  the viewport's width
	 * @param height the viewport's height
	 */
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
		if (zoom >= 0.4f && zoom <= 1.7f) camera.zoom = zoom;
		update();
	}

	/**
	 * translate the camera relative to its current position
	 *
	 * @param vector the vector to translate, given in x and y coordinates
	 */
	public void translateRelative(Vector2 vector) {
		camera.translate(vector);
		System.out.println(camera.position.toString());
		if (camera.position.x < 285) camera.position.set(new Vector2(285, camera.position.y), 0);
		else if (camera.position.x > 1680) camera.position.set(new Vector2(1680, camera.position.y), 0);
		else if (camera.position.y < 200) camera.position.set(new Vector2(camera.position.x, 200), 0);
		else if (camera.position.y > 10500) camera.position.set(new Vector2(camera.position.x, 10500), 0);
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

	/**
	 * Get the current zoom
	 * @return current zoom
	 * */
	public float getZoom() {
		return camera.zoom;
	}

	/**
	 * Get the camera of the manager.
	 * This should not be used in any final build!
	 * Instead, create a wrapper, so the camera isn't exposed directly!
	 * @deprecated
	 * @return the manager's camera
	 * */
	@Deprecated
	public OrthographicCamera getCamera() {
		return camera;
	}

	@Override
	public String toString() {
		return camera.position.toString() + ", zoom: " + camera.zoom;
	}
}