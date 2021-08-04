package sort;

import static sort.SortUtil.swap;

/**
 * Description:
 * 冒泡排序
 *
 * @author:edgarding
 * @date:2021/6/9
 **/
public class BubbleSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean isSwap = false;
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
        }
        return arr;
    }
}
