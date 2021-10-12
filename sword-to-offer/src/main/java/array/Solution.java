package array;

/**
 * Description:
 * 与数组相关的问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/10
 **/
public class Solution {
    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        // 关键在于不开辟一个新的空间
        int n;
        if ((n = nums.length) == 0) {
            return new int[] {};
        }
        for (int l = 0, r = n - 1; l < r; ) {
            while (l < r && nums[l] % 2 != 0) {
                l++;
            }
            while (l < r && nums[r] % 2 == 0) {
                r--;
            }
            if (l < r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
            l++;
            r--;
        }
        return nums;
    }

    /**
     * 剑指 Offer 57. 和为s的两个数字
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int l = 0, r = n - 1; l < r; ) {
            if (nums[l] + nums[r] == target) {
                return new int[] {nums[l], nums[r]};
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[] {};
    }
}
