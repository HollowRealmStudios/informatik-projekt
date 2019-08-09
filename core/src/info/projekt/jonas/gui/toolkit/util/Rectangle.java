package info.projekt.jonas.gui.toolkit.util;

public class Rectangle {

	public int x, y, width, height;

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return x + " / " + y + " / " + width + " / " + height;
	}

	public boolean contains(int x, int y) {
		return x >= this.x && x <= this.x + width && y >= this.y && this.y + this.height >= y;
	}
}
