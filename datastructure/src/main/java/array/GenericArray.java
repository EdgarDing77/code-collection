package array;

import java.util.Arrays;
import java.util.Objects;

/**
 * Description:
 * 优化在第一次设计数组上的疏忽和繁琐
 *
 * @author:edgarding
 * @date:2021/5/29
 **/
public class GenericArray<T> implements Generic<T> {
    private T[] data;
    private int size;
    private int capacity;
    private static final int DEFAULT_ARRAY_SIZE = 16;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public GenericArray() {
        capacity = DEFAULT_ARRAY_SIZE;
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public GenericArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity should greater than 0");
        }
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
        size = 0;
    }

    private boolean indexCheck(int index) {
        return index < size && index >= 0;
    }

    @Override
    public void add(int index, T value) {
        if (!indexCheck(index)) {
            return;
        }
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    private void ensureCapacity() {
        int curSize = size + 1;
        if (curSize >= capacity) {
            int oldCapacity = capacity;
            int newCapacity = (oldCapacity >> 1) + oldCapacity;
            if (newCapacity - MAX_ARRAY_SIZE > 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            this.capacity = newCapacity;
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        data[size++] = value;
    }

    @Override
    public T remove(int index) {
        if (!indexCheck(index)) {
            return null;
        }
        T elem = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return elem;
    }

    @Override
    public boolean set(int index, T value) {
        if (!indexCheck(index)) {
            return false;
        }
        data[index] = value;
        return true;
    }

    @Override
    public T get(int index) {
        if (!indexCheck(index)) {
            return null;
        }
        return data[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T obj) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenericArray<?> that = (GenericArray<?>) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, capacity);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
