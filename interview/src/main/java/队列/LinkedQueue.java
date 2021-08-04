package 队列;

import java.util.LinkedList;

/**
 * Description:
 * 链表实现队列
 *
 * @author:edgarding
 * @date:2021/6/4
 **/
public class LinkedQueue<E> implements Queue<E> {
    LinkedList<E> queue = new LinkedList<>();

    @Override
    public void offer(E e) {
        queue.addLast(e);
    }

    @Override
    public E poll() {
        return queue.pollFirst();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public E peek() {
        return queue.peek();
    }
}
