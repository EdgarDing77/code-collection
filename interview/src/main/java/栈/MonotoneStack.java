package 栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/7/15
 **/
public class MonotoneStack {
    /**
     * 数组中每一个数之后的第一比他大的元素
     * 下一个最大的元素
     */
    public static int[] nextGreatElement(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        // 从后往前遍历
        for (int i = nums.length - 1; i >= 0; i--) {
            // 不为空和栈顶元素小于当前元素的时候
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            // 栈底为最大的元素
            // 说明栈顶大于当前元素
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] res = new int[n1];
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            map.put(nums2[i], (stack.isEmpty() ? -1 : stack.peek()));
            stack.push(nums2[i]);
        }
        for (int i = 0; i < n1; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
