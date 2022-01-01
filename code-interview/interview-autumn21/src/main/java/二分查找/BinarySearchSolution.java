package 二分查找;

/**
 * Description:
 * 二分的一些问题
 *
 * @author:edgarding
 * @date:2021/7/4
 **/
public class BinarySearchSolution {
    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * <p>
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
     * <p>
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchTargetInSortNums(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }

        for (int l = 0, r = len - 1; l <= r; ) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[r]) {
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
                // 这里不能为else if，若target在数组中找不到，则会死循环
            } else {
                if (target < nums[mid] && target >= nums[l]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     */
    public int minArray(int[] numbers) {
        int n;
        if ((n = numbers.length) == 0) {
            return 0;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            } else if (numbers[mid] < numbers[r]) {
                r = mid;
            } else { // 去重
                r--;
            }
        }
        return numbers[l];
    }
}
