package info.projekt.jonas.rooms;

import info.projekt.jonas.gui.GuiProvider;
import info.projekt.jonas.gui.WorkshopGui;

@Buildable
public class WorkshopRoom extends Room {

    public WorkshopRoom() {
        super("Workshop", "Workshop/Workshop_1.png", "Workshop/Workshop_2.png", "Workshop/Workshop_3.png");
    }

	@Override
	public void clicked() {
		GuiProvider.requestGui(WorkshopGui.class).show();
	}

	@Override
    public void produce() {

    }

    @Override
    public void consume() {

    }

    @Override
    public boolean enoughResources() {
        return false;
    }
}
