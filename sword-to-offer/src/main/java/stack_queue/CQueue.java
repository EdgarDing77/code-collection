package stack_queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 * 剑指 Offer 09. 两个栈实现队列
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/28
 **/
public class CQueue {
    // JDK官方建议使用Deque完成Stack的操作，因为Stack操作开销大（每个方法带上了sychronized）
    Deque<Integer> stack1, stack2;

    public CQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.isEmpty() ? -1 : stack2.pop();
    }
}
