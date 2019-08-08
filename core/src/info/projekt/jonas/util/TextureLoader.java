package info.projekt.jonas.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class TextureLoader {

	private static final HashMap<String, Texture> TEXTURES = new HashMap<>();

	public static void loadTextures() {
		try {
			Files.walk(Paths.get(Gdx.files.internal("textures").path())).filter(path -> path.toString().endsWith(".png")).forEach(path -> TEXTURES.put(path.toString().replace("\\", "/"), new Texture(Gdx.files.internal(path.toString()))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Texture getTexture(String path) {
		if(TEXTURES.get(path) == null && TEXTURES.get("textures/" + path) == null) {
			System.out.println(TEXTURES.toString());
			Tuple<String, Integer> closest = new Tuple<>("??????", 0);
			TEXTURES.forEach((key, value) -> {
				if (FuzzySearch.ratio(path, key) > closest.getTwo()) {
					closest.setTwo(FuzzySearch.ratio(path, key));
					closest.setOne(key);
				}
			});
			throw new NullPointerException(path + " was not found. Did you mean " + closest.getOne());
		}
		if(TEXTURES.get(path) == null) return TEXTURES.get("textures/" + path);
		else TEXTURES.get(path);
		return null;
	}

}
