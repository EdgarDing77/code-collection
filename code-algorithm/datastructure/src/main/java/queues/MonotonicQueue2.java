package queues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/21
 **/
public class MonotonicQueue2 {

    class MonQueue {
        Deque<Integer> queue;

        public MonQueue() {
            queue = new LinkedList<>();
        }

        public void push(int val) {
            if (!queue.isEmpty() && queue.peekLast() < val) {
                queue.removeLast();
            }
            queue.push(val);
        }

        public int max() {
            return queue.peekFirst();
        }

        public void pop(int val) {
            if (queue.peekFirst() == val) {
                queue.pollFirst();
            }
        }
    }
}
