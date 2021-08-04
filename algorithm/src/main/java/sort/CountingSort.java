package sort;

import static sort.SortUtil.getMaxVal;
import static sort.SortUtil.getMinVal;

/**
 * Description:
 * 计数排序
 *
 * @author:edgarding
 * @date:2021/2/18
 **/
public class CountingSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        int min = getMinVal(arr);
        int max = getMaxVal(arr);
        int size = max - min + 1;
        int[] count = new int[size];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        for (int i = 0, j = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[j++] = i + min;
                count[i]--;
            }
        }
        return arr;
    }
}
