package stack_queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/24
 **/
public class MaxQueue {
    Deque<Integer> queue, helper;

    public MaxQueue() {
        queue = new LinkedList<>();
        helper = new LinkedList<>();
    }

    public int max_value() {
        return helper.isEmpty() ? -1 : helper.peek();
    }

    public void push_back(int value) {
        while (!helper.isEmpty() && value > helper.peekLast()) {
            helper.pollLast();
        }
        queue.offer(value);
        helper.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int val = queue.poll();
        if (helper.peek() == val) {
            helper.poll();
        }
        return val;
    }
}
