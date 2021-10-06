package array;

/**
 * Description:
 * 数组方法接口
 *
 * @author:edgarding
 * @date:2021/5/29
 **/
public interface Array {
    int get(int index);

    boolean add(int index, int value);

    int remove(int index);

    int set(int index, int value);

    boolean contains(int value);

    boolean isEmpty();

    int getSize();

    int getCapacity();
}