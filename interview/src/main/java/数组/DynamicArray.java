package 数组;

import java.util.Arrays;

/**
 * Description:
 * 目标：实现一个支持动态扩容的数组
 * 为了方便实现实现，采用整数型数组
 * <p>
 * <p>
 * 字段：
 * capacity
 * size
 * data []int
 * DEFAULT_CAPACITY
 * <p>
 * <p>
 * 方法：
 * add（）
 * remove（）
 * set()
 * get()
 * grow()
 *
 * @author:edgarding
 * @date:2021/5/29
 **/
public class DynamicArray extends NativeArray {
    private int[] data;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAXIMUM_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public DynamicArray() {
        this.capacity = DEFAULT_CAPACITY;
        this.data = new int[capacity];
        this.size = 0;
    }

    public DynamicArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity should greater than 0");
        }
        this.capacity = capacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    private boolean rangeCheck(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public int get(int index) {
        if (!rangeCheck(index)) {
            return -1;
        }
        return data[index];
    }

    private boolean ensureCapacity() {
        return size + 1 < capacity;
    }

    private void grow() {
        int oldCapacity = getCapacity();
        int newCapacity = (oldCapacity >> 1) + oldCapacity;
        if (newCapacity - MAXIMUM_ARRAY_SIZE > 0) {
            newCapacity = Integer.MAX_VALUE;
        }
        this.capacity = newCapacity;
        this.data = Arrays.copyOf(data, newCapacity);
    }

    public boolean add(int value) {
        if (!ensureCapacity()) {
            grow();
        }
        data[size++] = value;
        return true;
    }

    @Override
    public boolean add(int index, int value) {
        if (!rangeCheck(index)) {
            return false;
        }
        if (!ensureCapacity()) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        return true;
    }

    @Override
    public int remove(int index) {
        if (!rangeCheck(index)) {
            return -1;
        }
        int numRemove = size - index - 1;
        int elemRemove = data[index];
        if (numRemove > 0) {
            System.arraycopy(data, index + 1, data, index, numRemove);
        }
        data[size--] = 0;
        return elemRemove;
    }

    @Override
    public int set(int index, int value) {
        if (!rangeCheck(index)) {
            return -1;
        }
        int oldValue = data[index];
        data[index] = value;
        return oldValue;
    }

    /**
     * 判断元素是否存在
     *
     * @param value
     * @return
     */
    @Override
    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
}
