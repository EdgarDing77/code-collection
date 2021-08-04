package 散列表;

import java.util.LinkedHashMap;

/**
 * Description:
 * LRU缓存淘汰算法 Least Recently Used 最近最少使用 淘汰最近使用到的页面
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。
 * 缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 * <p>
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * @author:edgarding
 * @date:2021/6/12
 **/
public class LRU {
    /**
     * 通过list来保证堆栈页面的进入顺序
     * 通过HashMap来记录页面的位置，以便最快能查找到，进行页面置换
     */
    private LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;

    public LRU() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public LRU(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            this.capacity = DEFAULT_CAPACITY;
        }
    }

    /**
     * 获取页面
     *
     * @param key
     * @return
     */
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
        if (cache.size() >= this.capacity) {
            int oldKey = cache.keySet().iterator().next();
            cache.remove(oldKey);
        }
        cache.put(key, value);
    }
}
