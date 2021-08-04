package lru;

import java.util.HashMap;

/**
 * Description:
 * LRU缓存淘汰策略
 * 最近使用的数据是有用的
 * <p>
 * 1. cache必须有时序 -》 链表 -》 能分出最近使用和久未使用的数据，当容量满了之后，要删除最久未使用的那个元素腾出位置
 * 2. 要在cache中快速找到某个key是否存在并得到对应的val
 * 3. 每次访问cache中的某个key，需要将这个元素变为最近使用
 * <p>
 * 缓存结构
 * 通过双向链表使得能页面有序
 * 通过HashMap来实现O（1）的查找和删除
 * 为了方便LRU算法的操作，在这之上进行一层封装
 * 快速 Hash
 * 顺序 List
 *
 * @author:edgarding
 * @date:2021/7/20
 */
public class LRUCache {
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        cache = new DoubleList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            deleteKey(key);
            addRecently(key, val);
            return;
        }
        if (capacity == cache.size()) {
            removeLeastRecently();
        }
        addRecently(key, val);
    }

    /**
     * 最近未使用的元素位于链表头
     */
    private void removeLeastRecently() {
        Node deletedNode = cache.removeFirst();
        map.remove(deletedNode.key);
    }

    private void deleteKey(int key) {
        Node node = map.get(key);
        cache.remove(node);
        map.remove(key);
    }

    private void addRecently(int key, int val) {
        Node node = new Node(key, val);
        cache.addLast(node);
        map.put(key, node);
    }

    /**
     * hash表是无需动的
     *
     * @param key
     */
    private void makeRecently(int key) {
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }
}

/**
 * 保证head 永远是头 tail永远是尾部
 * 只能实现链表尾部添加，因此最近使用的节点位于末端
 */
class DoubleList {
    private Node head, tail;
    private int size;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail.prev;
        tail.next = head.prev;
        size = 0;
    }

    public void addLast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    /**
     * 移除最近没用的元素
     *
     * @return
     */
    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    public int size() {
        return this.size;
    }
}


/**
 * 链表节点
 */
class Node {
    public Node next, prev;
    public int key, val;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
