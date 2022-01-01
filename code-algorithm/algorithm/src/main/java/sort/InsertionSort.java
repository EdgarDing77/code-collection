package sort;

/**
 * Description:
 * 扑克理牌方式
 * 将合适大小的数插入到合适位置
 * 选定一个要插入的排 key，比key大的牌整体往后移动一位，将key插入
 *
 * @author:edgarding
 * @date:2021/6/10
 **/
public class InsertionSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > key; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
