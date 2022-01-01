package data_structure.lru;

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
 * @mail:edgarding97@gmail.com
 * @date:2021/11/1
 **/
public class MyLRU {
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int capacity;

    public MyLRU(int capacity) {
        cache = new DoubleList();
        map = new HashMap<>();
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
            delete(key);
            addRecently(key, val);
            return;
        }
        if (capacity == cache.size) {
            removeLeastRecently();
        }
        addRecently(key, val);
    }

    private void addRecently(int key, int val) {
        Node node = new Node(key, val);
        map.put(key, node);
        cache.add(node);
    }

    private void removeLeastRecently() {
        Node delNode = cache.removeFirst();
        map.remove(delNode.key);
    }

    private void delete(int key) {
        Node node = map.get(key);
        cache.remove(node);
        map.remove(key);
    }

    private void makeRecently(int key) {
        Node node = map.get(key);
        cache.remove(node);
        cache.add(node);
    }
}

class DoubleList {
    public int size;
    private Node head, tail;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail.prev;
        tail.next = head.prev;
        size = 0;
    }

    public void add(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    // 移除最近没用的元素
    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node delNode = head.next;
        remove(delNode);
        return delNode;
    }
}

class Node {
    public Node next, prev;
    public int key, val;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
