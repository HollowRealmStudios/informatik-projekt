package info.projekt.jonas.util;

public class MyNameJeffException extends RuntimeException {

	@Override
	public void printStackTrace() {
		System.err.println("My name Jeff");
	}
}
