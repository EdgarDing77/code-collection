package sort;

import static sort.SortUtil.swap;

/**
 * Description:
 * 直接选择排序
 * 在未排序中的序列中找到一个最小的元素，放入到排序完的最后一位。
 *
 * @author:edgarding
 * @date:2021/6/10
 **/
public class SelectionSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
        return arr;
    }
}
