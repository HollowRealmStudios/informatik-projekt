package info.projekt.jonas.gui;

public abstract class GuiComponent {

	private boolean visible = true;
	protected int x, y;

	public void show() {
		visible = true;
	}

	public void hide() {
		visible = false;
	}

	public abstract void paint(Object o);
}
