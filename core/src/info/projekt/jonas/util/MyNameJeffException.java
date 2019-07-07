package info.projekt.jonas.util;

/**
 * @author Jonas
 */
public class MyNameJeffException extends RuntimeException {

	@Override
	public void printStackTrace() {
		System.err.println("My name Jeff");
	}
}
