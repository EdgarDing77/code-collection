package 二分查找;

/**
 * Description:
 * 约束 -》 有序数组给定某个数组的索引
 * 1、实现有序数组的二分查找
 * 2、实现模糊二分查找算法（比如大于等于给定值的第一个元素）
 *
 * @author:edgarding
 * @date:2021/6/4
 **/
public class BinarySearch {
    public static int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else if (target == nums[mid]) {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 左边界查找 -》 左边第一个元素
     *
     * @param nums
     * @param target
     * @return
     */
    public static int leftBoundSearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else if (target == nums[mid]) {
                r = mid - 1;
            }
        }
        if (l >= nums.length || nums[l] != target) {
            return -1;
        }
        return l;
    }

    /**
     * 右边界查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int rightBoundSearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else if (target == nums[mid]) {
                l = mid + 1;
            }
        }
        if (r < 0 || nums[r] != target) {
            return -1;
        }
        return r;
    }

    /**
     * 搜索左右边界
     *
     * @param nums
     * @param target
     * @param isLeftOrRight true -> left falst -> right
     * @return
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
}
