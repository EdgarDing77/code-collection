package 数组;

import java.util.*;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/7/10
 **/
public class ArraySolution {
    /**
     * 区间合并问题
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 贪心
        int n = intervals.length;
        if (n == 0) {
            return null;
        }
        HashMap<Integer, int[]> map = new HashMap<>();
        List<int[]> rec = new ArrayList<>();
        // 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] > o2[0]) {
                    return 1;
                } else {
                    // 子区间可以通过最有边界过滤
                    return o2[1] - o1[1];
                }
            }
        });
        // 最右边界
        int r = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int[] tmp = intervals[i];
            if (intervals[i][1] <= r) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (intervals[j][0] > tmp[1]) {
                    break;
                } else if (intervals[j][0] <= tmp[1] && intervals[j][1] > tmp[1]) {
                    tmp[1] = intervals[j][1];
                }
            }
            rec.add(tmp);
            if (r < tmp[1]) {
                r = tmp[1];
            }
        }
        int[][] res = new int[rec.size()][2];
        for (int i = 0; i < rec.size(); i++) {
            res[i] = rec.get(i);
        }
        return res;
    }


    /**
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * <p>
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * <p>
     * 必须 原地 修改，只允许使用额外常数空间。
     * <p>
     * Q.1 2 3 4 5
     * A.1 2 3 5 4
     * <p>
     * Q.1 5 4 3 2
     * A. 2 1 3 4 5
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        // 数组中升序的最后一位
        int l = 0, r = n - 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                l = i;
            }
        }
        // 之后查找降序的第一位
        for (int i = l + 1; i < n; i++) {
            if (nums[i] > nums[l]) {
                r = i;
            }
        }
        // 交换两个位置的数
        swap(nums, l, r);
        // 进行排序 对降序的
        Arrays.sort(nums, l + 1, n);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 数组中第k大的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    /**
     * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     * <p>
     * 找到所有出现两次的元素。
     * <p>
     * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
     * <p>
     * 注意1 ≤ a[i] ≤ n
     * 通过这个来在原地数组进行记录
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[(nums[i] - 1) % n] += n;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 2 * n) ret.add(i + 1);
        }
        return ret;
    }
}
