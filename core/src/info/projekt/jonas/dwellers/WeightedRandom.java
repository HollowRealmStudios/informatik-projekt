package info.projekt.jonas.dwellers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class WeightedRandom {

    private static final HashMap<Integer, Integer> DEFAULT_MAP = new HashMap<>();

    static {
        DEFAULT_MAP.put(1, 20);
        DEFAULT_MAP.put(2, 45);
        DEFAULT_MAP.put(3, 20);
        DEFAULT_MAP.put(4, 5);
        DEFAULT_MAP.put(5, 5);
        DEFAULT_MAP.put(6, 1);
        DEFAULT_MAP.put(7, 1);
        DEFAULT_MAP.put(8, 1);
        DEFAULT_MAP.put(9, 1);
        DEFAULT_MAP.put(10, 1);
    }

    //10pnt - 10%
    public static int newInt(HashMap<Integer, Integer> hashMap) {
        ArrayList<Integer> full = new ArrayList<>();
        for (Map.Entry<Integer, Integer> next : hashMap.entrySet()) {
            for (int i = 0; i < next.getValue(); i++) {
                full.add(next.getKey());
            }
        }
        return full.get(ThreadLocalRandom.current().nextInt(0, full.size()));
    }

    public static int newInt() {
        ArrayList<Integer> full = new ArrayList<>();
        for (Map.Entry<Integer, Integer> next : DEFAULT_MAP.entrySet()) {
            for (int i = 0; i < next.getValue(); i++) {
                full.add(next.getKey());
            }
        }
        return full.get(ThreadLocalRandom.current().nextInt(0, full.size()));
    }

}
