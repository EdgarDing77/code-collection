package sort;

import java.util.Random;

import static sort.SortUtil.swap;


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
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
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
     * 我们需要实时维护两个指针使得任意时候，对于任意数组下标 kk，我们有如下条件成立：
     * <p>
     * l\leq k\leq il≤k≤i 时，\textit{nums}[k]\leq \textit{pivot}nums[k]≤pivot。
     * <p>
     * i+1\leq k\leq j-1i+1≤k≤j−1 时，\textit{nums}[k]> \textit{pivot}nums[k]>pivot。
     * <p>
     * k==rk==r 时，\textit{nums}[k]=\textit{pivot}nums[k]=pivot。
     * <p>
     * 我们每次移动指针 jj ，如果 \textit{nums}[j]> pivotnums[j]>pivot，我们只需要继续移动指针 jj ，即能使上述三个条件成立，否则我们需要将指针 ii 加一，然后交换 \textit{nums}[i]nums[i] 和 \textit{nums}[j]nums[j]，再移动指针 jj 才能使得三个条件成立。
     * <p>
     * 当 jj 移动到 r-1r−1 时结束循环，此时我们可以由上述三个条件知道 [l,i][l,i] 的数都小于等于主元 pivot，[i+1,r-1][i+1,r−1] 的数都大于主元 pivot，那么我们只要交换 \textit{nums}[i+1]nums[i+1] 和 \textit{nums}[r]nums[r] ，即能使得 [l,i+1][l,i+1] 区间的数都小于 [i+2,r][i+2,r] 区间的数，完成一次划分，且分界值下标为 i+1i+1，返回即可。
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
