package 链表;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/5/31
 **/
public interface List {

    int size();

    boolean add(int val);

    boolean add(int index, int val);

    /**
     * return removed element value
     *
     * @param index
     * @return
     */
    int remove(int index);

    int set(int index, int newVal);

    boolean isEmpty();

    int indexOf(int val);

    int get();

    int get(int index);

    void print();
}
