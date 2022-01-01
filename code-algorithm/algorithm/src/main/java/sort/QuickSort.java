package sort;

import static sort.SortUtil.swap;

import java.util.Random;


/**
 * Description:
 * 快速排序
 *
 * @author:edgarding
 * @date:2021/6/11
 **/
public class QuickSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if (arr[j] <= pivot) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    /**
     * 优化
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        randomizedQuicksort(nums, 0, nums.length - 1);
        return nums;
    }

    public void randomizedQuicksort(int[] nums, int l, int r) {
        if (l < r) {
            int pos = randomizedPartition(nums, l, r);
            randomizedQuicksort(nums, l, pos - 1);
            randomizedQuicksort(nums, pos + 1, r);
        }
    }

    public int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l; // 随机选一个作为我们的主元
        swap(nums, r, i);
        return partition2(nums, l, r);
    }

    /**
     * 整个划分函数 partition 主要涉及两个指针 ii 和 jj，一开始 i = l - 1，j = l。
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public int partition2(int[] nums, int l, int r) {
        int pivot = nums[r];
        // 维护两个指针  将小于pivot的都换到之前
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }
}
