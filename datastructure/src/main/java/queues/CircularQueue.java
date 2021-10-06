package queues;

/**
 * Description:
 * 循环队列
 * 如何判断
 *
 * @author:edgarding
 * @date:2021/6/4
 **/
public class CircularQueue implements Queue<Integer> {
    private int[] items;
    private int size;
    private final int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    private int head;
    private int tail;

    public CircularQueue() {
        capacity = DEFAULT_CAPACITY;
        items = new int[capacity];
    }

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            this.capacity = DEFAULT_CAPACITY;
        } else {
            this.capacity = capacity;
        }
        items = new int[this.capacity];
    }


    @Override
    public void offer(Integer integer) {
        // 队列满了
        if ((tail + 1) % size == head) {
            return;
        }
        items[tail++] = integer;
        tail = (tail + 1) % size;
        size++;
    }

    @Override
    public Integer poll() {
        if (head == tail || size == 0) {
            return null;
        }
        int res = items[head];
        size--;
        head = (head + 1) % size;
        return res;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer peek() {
        return items[head];
    }
}
