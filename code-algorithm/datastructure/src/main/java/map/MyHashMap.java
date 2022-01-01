package map;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Description:
 * 一个简单的HashMap实现
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/5
 **/
public class MyHashMap<K, V> {
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Node) {
                Node<?, ?> node = (Node<?, ?>) obj;
                return Objects.equals(node.key, this.getKey()) && Objects.equals(node.value, this.getValue());
            }
            return false;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

    Node<K, V>[] table;
    int size;

    public MyHashMap(int size) {
        this.table = new Node[size];
        this.size = size;
    }

    public int hash(K key) {
        return Objects.hashCode(key) % size;
    }

    public void put(K key, V value) {
        Node<K, V> node = new Node<>(hash(key), key, value, null);
        if (table[node.hash] == null) {
            table[node.hash] = node;
        } else {
            Node<K, V> head = table[node.hash];
            while (head.next != null) {
                head = head.next;
            }
            head.next = node;
        }
    }

    public Node<K, V> get(K key) {
        int idx = hash(key);
        if (table[idx] == null) {
            return null;
        } else if (table[idx].next == null && table[idx].getKey().equals(key)) {
            return table[idx];
        } else {
            Node<K, V> cur = table[idx];
            while (!cur.getKey().equals(key)) {
                cur = cur.next;
            }
            return cur;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>(10);
        List<String> rec = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            String key = String.valueOf(random.nextInt(1000) * i);
            map.put(key, i);
            rec.add(key);
        }
        for (String key : rec) {
            System.out.println(map.get(key).toString());
        }
    }
}
