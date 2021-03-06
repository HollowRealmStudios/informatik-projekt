package info.projekt.jonas.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class TextureLoader {

	private static final HashMap<String, Texture> TEXTURES = new HashMap<>();

	public static void loadTextures() {
		try {
			Files.walk(Paths.get(Gdx.files.internal("textures").path())).filter(path -> path.toString().endsWith(".png")).forEach(path -> {
				String[] split = path.toString().replace("\\", "/").split("/");
				int size = split.length;
				if(split[size - 1].replace(".png", "").length() == 1) TEXTURES.put(split[size - 2].concat("/").concat(split[size - 1]), new Texture(Gdx.files.internal(path.toString().replace("\\", "/"))));
				else TEXTURES.put(split[size - 1], new Texture(Gdx.files.internal(path.toString().replace("\\", "/"))));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		TEXTURES.entrySet().forEach(set -> System.out.println(set.toString()));
	}

	@Nullable
	public static Texture getTexture(String path) {
		if (TEXTURES.get(path) == null) {
			Tuple<String, Integer> closest = new Tuple<>("??????", 0);
			TEXTURES.forEach((key, value) -> {
				if (FuzzySearch.ratio(path, key) > closest.getTwo()) {
					closest.setTwo(FuzzySearch.ratio(path, key));
					closest.setOne(key);
				}
			});
			throw new NullPointerException(path + " was not found. Did you mean " + closest.getOne());
		}
		else return TEXTURES.get(path);
	}

	@NotNull
	@Contract("_ -> new")
	public static Texture getTextureUnsafe(String path) {
		return new Texture(Gdx.files.internal(path));
	}

}
