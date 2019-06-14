package info.projekt.jonas.rooms;

import info.projekt.InfoProjekt;

public class Notifier {


    public enum NOTIFICATION {PLACED, UPGRADED, DWELLER}

    private final Thread thread;
    private boolean newDweller;
    private boolean roomPlaced;
    private boolean roomUpgraded;

    public Notifier(int delay) {
        thread = new Thread(() -> {
            while (true) {
                for (Room[] rooms : InfoProjekt.GAME_STORAGE.getRooms()) {
                    for (Room room : rooms) {
                        if (room != null) {
                            room.onTick();
                            if (newDweller) newDweller = false;
                            room.onNewDweller();
                            if (roomPlaced) roomPlaced = false;
                            room.onPlace();
                            if (roomUpgraded) roomUpgraded = false;
                            room.onUpgrade();
                        }
                    }
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.stop();
    }

    public void notify(NOTIFICATION notification) {
        switch (notification) {
            case PLACED:
                roomPlaced = true;
                break;
            case DWELLER:
                newDweller = true;
                break;
            case UPGRADED:
                roomUpgraded = true;
                break;
        }
    }
}
