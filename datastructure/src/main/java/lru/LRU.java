package lru;

import java.util.LinkedHashMap;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/2/24
 **/
public class LRU {
    int cap = 0;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRU(int cap) {
        this.cap = cap;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
            return;
        }
        if (cache.size() >= this.cap) {
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key, value);
    }
}
