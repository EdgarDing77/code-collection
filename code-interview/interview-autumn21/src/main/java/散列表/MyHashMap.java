package 散列表;

import java.util.Objects;

/**
 * Description:
 * 基于拉链法实现Hash冲突
 * 简化实现HashMap
 * <p>
 * <p>
 * 区别：
 * 1.HashMap中当链表长度大于等于TREEIFY_THRESHOLD = 8;时会转化为红黑树
 * 2.小于等于UNTREEIFY_THRESHOLD = 6;时会退化成链表
 * 3.且当桶的容量 >= 64才会进行树的变化
 *
 * @author:edgarding
 * @date:2021/6/12
 **/
public class MyHashMap<K, V> implements MyMap<K, V> {
    /**
     * loadfactor 反应该hashmap的桶的使用情况
     * loadFactor 增大 （可大于1）-》桶空间使用率高
     * loadFactor 减少 健与健之间的碰撞变少
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * hash table
     */
    Node<K, V>[] table;


    /*
        static utils
     */
    /**
     * the number of k-v mappings in this map
     */
    int size;

    /*
        Fields
     */
    /**
     * 阈值 下一个扩容的值 （capacity * load factor）
     */
    int threshold;
    float loadFactor;
    /**
     * 操作数
     */
    int modCount;

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /*
        operations
     */

    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * 将数进行充分hash
     * 计算余数时，由于 n 比较小，hash 只有低16位参与了计算，高位的计算可以认为是无效的。这样导致了计算结果只与低位信息有关，高位数据没发挥作用。
     * 为了处理这个缺陷， hash 高16位数据与低16位数据进行异或运算，
     *
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >> 16);
    }

    /**
     * 找到大于或等于 cap 的最小2的幂
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode((hash(key)), key) != null;
    }

    @Override
    public V get(Object key) {
        Node<K, V> e;
        return ((e = getNode(hash(key), key)) == null ? null : e.value);
    }

    private final Node<K, V> getNode(int hash, Object key) {
        Node<K, V>[] tab;
        Node<K, V> first, e;
        int n;
        K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k)))) {
                return first;
            }
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash && ((k = e.key) == key ||
                        (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false);
    }

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(hash, key, value, next);
    }

    /**
     * put k-v into map
     * hash的方式为 tab.length - 1 & hash(key)
     * todo：理解不够
     *
     * @param hash
     * @param key
     * @param value
     * @param onlyIfAbsent if true, don't change existing value
     * @return
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((p = tab[i = (n - 1) & hash]) == null) {
            tab[i] = newNode(hash, key, value, null);
        } else {
            // 出现hash冲突 -》 拉链法
            Node<K, V> e;
            K k;
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                }
                // 存在key
                if (e.hash == hash && ((k = e.key) == key ||
                    (key != null && key.equals(k)))) {
                    break;
                }
                p = e;
            }
        }
        ++modCount;
        if (++size > threshold) {
            resize();
        }
        return null;
    }

    /**
     * 在 HashMap 中，桶数组的长度均是2的幂，阈值大小为桶数组长度与负载因子的乘积。当 HashMap 中的键值对数量超过阈值时，进行扩容。
     * <p>
     * HashMap 的扩容机制与其他变长集合的套路不太一样，HashMap 按当前桶数组长度的2倍进行扩容，阈值也变为原来的2倍（如果计算过程中，阈值溢出归零，则按阈值公式重新计算）。扩容之后，要重新计算键值对的位置，并把它们移动到合适的位置上去。
     * 1.计算新桶数组的容量 newCap 和新阈值 newThr
     * 2.根据计算出的 newCap 创建新的桶数组，桶数组 table 也是在这里进行初始化的
     * 3.将键值对节点重新映射到新的桶数组里。节点按原顺序进行分组。
     *
     * @return
     */
    final Node<K, V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                oldCap >= DEFAULT_CAPACITY) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = DEFAULT_CAPACITY;
            newThr = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
        }
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; j++) {
                Node<K, V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null) {
                        newTab[e.hash & (newCap - 1)] = e;
                    } else {
                        // 这里的分组注意：
                        // 容量的变化 -》 之间的hash位置是 （n-1） & （hash key）
                        // 现在 n << 1 n 变成 n+1位
                        // todo： 细致分析
                        Node<K, V> loHead = null, loTail = null;
                        Node<K, V> hiHead = null, hiTail = null;
                        Node<K, V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null) {
                                    loHead = e;
                                } else {
                                    loTail.next = e;
                                }
                                loTail = e;
                            } else {
                                if (hiTail == null) {
                                    hiHead = e;
                                } else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = next;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> e;
        return (e = removeNode(hash(key), key, null, false)) == null ?
            null : e.value;
    }

    /**
     * @param hash
     * @param key
     * @param value
     * @param matchValue 只有值相等时才移动
     * @return
     */
    final Node<K, V> removeNode(int hash, Object key, Object value,
                                boolean matchValue) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (p = tab[index = (n - 1) & hash]) != null) {
            // 记录删除的节点
            Node<K, V> node = null, e;
            K k;
            V v;
            if (p.hash == hash && ((k = p.key) == key ||
                (key != null && key.equals(k)))) {
                node = p;
            } else if ((e = p.next) != null) {
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key ||
                            (key != null && key.equals(k)))) {
                        node = e;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);
            }
            if (node != null && (!matchValue || ((v = node.value) == value) ||
                (value != null && value.equals(v)))) {
                if (node == p) {
                    tab[index] = node.next;
                } else {
                    p.next = node.next;
                }
                modCount++;
                --size;
                return node;
            }
        }
        return null;
    }

    @Override
    public void clear() {

    }

    private static class Node<K, V> implements MyMap.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }


        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof MyMap.Entry) {
                MyMap.Entry<?, ?> e = (MyMap.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public final int hashCode() {
            return Objects.hash(key) ^ Objects.hashCode(value);
        }
    }
}
