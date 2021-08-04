package 堆;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/22
 **/
public class HeapSortFinal {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        // 默认建立最大堆
        buildHeap(arr, arr.length);

        for (int j = arr.length - 1; j > 0; j--) {
            // 将堆顶最大的元素进行交换
            swap(arr, 0, j);
            maxHeap(arr, 0, j);
        }
    }

    public static void buildHeap(int[] arr, int heapSize) {
        // 堆公式 -》 root = 2 * i
        // left=2*i+1 right=2*i+2
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeap(arr, i, heapSize);
        }
    }

    /**
     * 最小堆
     *
     * @param arr
     * @param heapSize
     * @param i
     */
    public static void minHeap(int[] arr, int i, int heapSize) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int minValIndex = i;
        if (left < heapSize && arr[left] < arr[minValIndex]) {
            minValIndex = left;
        }
        if (right < heapSize && arr[right] < arr[minValIndex]) {
            minValIndex = right;
        }
        // 子节点比当前小
        if (minValIndex != i) {
            swap(arr, i, minValIndex);
            // 判断子节点是否打破了最小堆的性质
            minHeap(arr, minValIndex, heapSize);
        }
    }

    /**
     * 大根堆
     *
     * @param arr
     * @param i
     * @param heapSize
     */
    public static void maxHeap(int[] arr, int i, int heapSize) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int maxValIndex = i;
        if (left < heapSize && arr[left] > arr[maxValIndex]) {
            maxValIndex = left;
        }
        if (right < heapSize && arr[right] > arr[maxValIndex]) {
            maxValIndex = right;
        }
        if (maxValIndex != i) {
            swap(arr, i, maxValIndex);
            maxHeap(arr, maxValIndex, heapSize);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
