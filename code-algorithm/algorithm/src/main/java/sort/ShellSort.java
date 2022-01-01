package sort;

/**
 * Description:
 * 希尔排序
 * 1、先选择一个增量序列
 * 2、按增量序列的个数k，进行k趟排序
 *
 * @author:edgarding
 * @date:2021/2/17
 **/
public class ShellSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        int gap = 1, len = arr.length;
        while (gap > len / 3) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3) {
            for (int i = gap; i < len; i++) {
                int temp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
        }
        return arr;
    }
}
