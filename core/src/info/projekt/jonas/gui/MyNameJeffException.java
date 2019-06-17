package info.projekt.jonas.gui;

public class MyNameJeffException extends RuntimeException {

	@Override
	public void printStackTrace() {
		System.err.println("My name Jeff");
	}
}
