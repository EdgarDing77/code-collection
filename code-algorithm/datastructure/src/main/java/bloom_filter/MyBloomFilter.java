package bloom_filter;

import java.util.BitSet;

/**
 * Description:
 * Bloom过滤器的实现
 *
 * @author:edgarding
 * @date:2021/2/24
 **/
public class MyBloomFilter {
    /**
     * 位数组的默认大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;
    /**
     * 哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};
    /**
     * 位数组：数组中的元素只能是0或者1
     */
    private BitSet bits;
    /**
     * 存放hash函数
     */
    private SimpleHash[] func;

    public MyBloomFilter() {
        bits = new BitSet(DEFAULT_SIZE);
        func = new SimpleHash[SEEDS.length];
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     */
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 静态内部类。用于 hash 操作！
     */
    public static class SimpleHash {

        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }

    }
}
