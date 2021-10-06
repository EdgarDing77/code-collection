package search_algorithem;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/1
 **/
public class EasySolution {
    /**
     * 剑指 Offer 03. 数组中重复的数字
     * 找出数组中重复的数字。
     * <p>
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     */
    public int findRepeatNumber(int[] nums) {
        int n;
        if ((n = nums.length) == 0) {
            return -1;
        }
        int[] rec = new int[n];
        for (int i = 0; i < nums.length; i++) {
            if (rec[nums[i]] >= 1) {
                return nums[i];
            }
            rec[nums[i]]++;
        }
        return -1;
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * 统计一个数字在排序数组中出现的次数。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n;
        if ((n = nums.length) == 0) {
            return 0;
        }
        int l = 0, r = n - 1, lb = 0, rb = 0;
        lb = binarySearch(nums, target, true);
        rb = binarySearch(nums, target, false);
        if (lb == -1 || rb == -1) {
            return 0;
        }
        return rb - lb + 1;
    }

    /**
     * 左右边界二分查找，返回边界下标
     *
     * @param nums
     * @param target
     * @param isLeftOrRight true 查找左边界 false 查找右边界
     * @return 返回边界下标
     */
    private int binarySearch(int[] nums, int target, boolean isLeftOrRight) {
        int l = 0, r = nums.length - 1;
        for (; l <= r; ) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                if (isLeftOrRight) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        if (isLeftOrRight && l < nums.length && nums[l] == target) {
            return l;
        } else if (!isLeftOrRight && r >= 0 && nums[r] == target) {
            return r;
        } else {
            return -1;
        }
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n;
        if ((n = nums.length) == 0) {
            return -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] != mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
