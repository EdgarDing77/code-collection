package 数组;

/**
 * Description:
 * 增删改查
 *
 * @author:edgarding
 * @date:2021/5/29
 **/
public interface Generic<T> {
    void add(int index, T value);

    void add(T value);

    T remove(int index);

    boolean set(int index, T value);

    T get(int index);

    boolean isEmpty();

    boolean contains(T obj);
}
