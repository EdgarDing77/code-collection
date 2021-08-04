package sort;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/12
 **/
public class TopK {
    /**
     * 在数组中找到前k大的元素
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] largestK(int[] arr, int k) {
        int n = arr.length;
        if (n == 0 || k == 0) {
            return new int[]{};
        }
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else if (minHeap.peek() < num) {
                minHeap.remove();
                minHeap.add(num);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    /**
     * 最小的k个数
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK(int[] arr, int k) {
        int n = arr.length;
        if (n == 0 || k == 0) {
            return new int[]{};
        }
        Queue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        for (int i = 0; i < n; i++) {
            if (maxHeap.size() < k) {
                maxHeap.offer(arr[i]);
            } else if (maxHeap.peek() > arr[i]) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll();
        }
        return res;
    }

    /**
     * 数组中第k大的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 0) {
            return -1;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (minHeap.size() < k) {
                minHeap.add(nums[i]);
            } else if (minHeap.peek() < nums[i]) {
                minHeap.remove();
                minHeap.add(nums[i]);
            }
        }
        return minHeap.peek();
    }
}
