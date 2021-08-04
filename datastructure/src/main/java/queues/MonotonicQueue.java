package queues;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * 单调队列
 * 解决滑动窗口最大值问题
 *
 * @author:edgarding
 * @date:2021/1/17
 **/
public class MonotonicQueue {
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonQueue window = new MonQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                window.push(nums[i]);
            } else {
                window.push(nums[i]);
                res.add(window.max());
                window.pop(nums[i - k + 1]);
            }
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    class MonQueue {
        LinkedList<Integer> queue;

        public MonQueue() {
            queue = new LinkedList<>();
        }

        public void push(int n) {
            if (!queue.isEmpty() && n > queue.peekLast()) {
                queue.removeLast();
            }
            queue.addLast(n);
        }

        public int max() {
            return queue.peekFirst();
        }

        public void pop(int n) {
            if (queue.peekFirst() == n) {
                queue.pollFirst();
            }
        }
    }
}
