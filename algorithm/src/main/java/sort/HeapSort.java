package sort;


import static sort.SortUtil.swap;

/**
 * Description:
 * 堆排序
 * 1. 先将初始序列K[1..n]建成一个大顶堆, 那么此时第一个元素K1最大, 此堆为初始的无序区.
 * 2. 再将关键字最大的记录K1 (即堆顶, 第一个元素)和无序区的最后一个记录 Kn 交换, 由此得到新的无序区K[1..n−1]和有序区K[n], 且满足K[1..n−1].keys⩽K[n].key
 * 3. 交换K1 和 Kn 后, 堆顶可能违反堆性质, 因此需将K[1..n−1]调整为堆. 然后重复步骤②, 直到无序区只有一个元素时停止.
 * <p>
 * 节点k的左子节点：2*k+1
 * 节点k的右子节点：2*k+2
 * 父节点：floor(k-1/2)
 *
 * @author:edgarding
 * @date:2021/6/13
 **/
public class HeapSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        // 1.构建大顶堆
        int n = arr.length;
        buildHeap(arr, arr.length);
        // 2.调整堆结构 + 交换堆顶与末尾元素 -> 从而达到排序的目的
        for (int j = n - 1; j > 0; j--) {
            // 堆顶的元素由于是最大的，与末尾元素进行交换
            // 使得arr[j]是所有元素中最大的数
            swap(arr, 0, j);
            // 删除最大元素 j
            maxHeapify(arr, 0, j);
        }
        return arr;
    }

    private static void buildHeap(int[] arr, int len) {
        for (int i = len / 2; i >= 0; i--) {
            maxHeapify(arr, i, len);
        }
    }

    private static void maxHeapify(int[] arr, int i, int len) {
        for (; (i << 1) < len; ) {
            int left = i * 2, right = i * 2 + 1, largest = i;
            if (left < len && arr[largest] < arr[left]) {
                largest = left;
            }
            if (right < len && arr[largest] < arr[right]) {
                largest = right;
            }
            if (largest != i) {
                swap(arr, i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }
}




