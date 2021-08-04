package 队列;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/4
 **/
public interface Queue<E> {
    void offer(E e);

    E poll();

    int size();

    boolean isEmpty();

    E peek();

}
