package info.projekt.jonas.gui.toolkit.util;

import info.projekt.jonas.gui.toolkit.Layer;

public class LayerRequest {

	public final boolean force;
	public final Class<? extends Layer> layer;
	public final int layerNumber;

	public LayerRequest(Class<? extends Layer> layer, int layerNumber, boolean force) {
		this.layer = layer;
		this.layerNumber = layerNumber;
		this.force = force;
	}

}
