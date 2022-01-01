package stacks;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/1/16
 **/
public class MonotoneStack {
    public static int[] nextGreatElement(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        System.out.println(Arrays.toString(nextGreatElement(nums)));

    }
}
