package info.projekt.jonas.gui.toolkit.util;

import org.jetbrains.annotations.Contract;

public class Rectangle {

	public int x;
	public int y;
	public int width;
	public int height;

	@Contract(pure = true)
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

	public void setAll(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
