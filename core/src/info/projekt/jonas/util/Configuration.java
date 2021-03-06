package info.projekt.jonas.util;

public class Configuration {

	public static final class Debug {
		public static final boolean FPS_COUNTER = false;
		public static final boolean GUI_DEBUG = false;
		public static final boolean REGISTRY_DEBUG = false;
	}

	public static final class Resources {
		public static final float MINUTES_PER_DWELLER = 0.2f;
		public static final float RESOURCE_MULTIPLIER = 1f;
		public static final float MINUTES_PER_CHILDREN = 1f;
		public static final float MONEY_MULTIPLIER = 2f;
	}

	public static final String FILE_NAME = "Storage.dat";
	public static final int ROOMS_HOR = 5;
	public static final int ROOMS_VER = 15;
	public static final int BACKGROUND_MARGIN = 30;

}
