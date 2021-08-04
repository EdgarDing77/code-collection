package 队列;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/4
 **/
public class ArrayQueue<E> implements Queue<E> {
    private List<E> queue = new ArrayList<>();

    @Override
    public void offer(E e) {
        queue.add(e);
    }

    @Override
    public E poll() {
        if (!queue.isEmpty()) {
            return queue.remove(0);
        }
        return null;
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
        if (!queue.isEmpty()) {
            return queue.get(0);
        }
        return null;
    }

}
