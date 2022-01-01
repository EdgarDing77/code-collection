package sort;

/**
 * Description:
 * 归并排序
 * 对数组进行递归，将排序的子数组依次进行归并排序
 *
 * @author:edgarding
 * @date:2021/6/11
 **/
public class MergeSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + ((right - left) >> 1);
            // 注意这里递归调用是mid的值
            // 如果以left ～ mid-1 可能会stackoverflow
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int size = right - left + 1;
        // 创建一个size大小的数组存放归并后的数组
        int[] temp = new int[size];
        int i = left, j = mid + 1, index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, left, size);
    }

}
