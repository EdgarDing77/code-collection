package 栈;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/4
 **/
public interface Stack<E> {
    void push(E e);

    E pop();

    boolean isEmpty();

    int size();

    void print();
}
