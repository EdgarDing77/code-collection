package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 与数组相关的问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/10
 **/
public class Solution {
    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        // 关键在于不开辟一个新的空间
        int n;
        if ((n = nums.length) == 0) {
            return new int[] {};
        }
        for (int l = 0, r = n - 1; l < r; ) {
            while (l < r && nums[l] % 2 != 0) {
                l++;
            }
            while (l < r && nums[r] % 2 == 0) {
                r--;
            }
            if (l < r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
            l++;
            r--;
        }
        return nums;
    }

    /**
     * 剑指 Offer 57. 和为s的两个数字
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int l = 0, r = n - 1; l < r; ) {
            if (nums[l] + nums[r] == target) {
                return new int[] {nums[l], nums[r]};
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[] {};
    }

    /**
     * 剑指 Offer 66. 构建乘积数组
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
/*        int n;
        if((n = a.length) == 0) {
            return new int[]{};
        }
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[n];
        left[0] = a[0];
        right[n-1] = a[n-1];
        for(int i=1; i<n; i++) {
            left[i] = left[i-1] * a[i];
        }
        for(int i=n-1; i>=0; i--) {
            right[i] = right[i+1] * a[i];
        }
        for(int i=0; i<n; i++) {
            if(i == 0) {
                res[i] = right[i + 1];
            } else if(i == n-1) {
                res[i] = left[i-1];
            } else {
                res[i] = left[i-1] * right[i + 1];
            }
        }
        return res;*/

        int[] res = new int[a.length];
        for (int i = 0, cur = 1; i < a.length; i++) {
            res[i] = cur;   // 先乘左边的数(不包括自己)
            cur *= a[i];
        }
        for (int i = a.length - 1, cur = 1; i >= 0; i--) {
            res[i] *= cur;  // 再乘右边的数(不包括自己)
            cur *= a[i];
        }
        return res;
    }

    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     *
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        for (int l = 1, r = 1, sum = 0; r < target; r++) {
            sum += r;
            while(sum > target) {
                sum -= l++;
            }
            if(sum == target) {
                int[] tmp = new int[r - l + 1];
                for(int i=0; i<tmp.length; i++) {
                    tmp[i] = l + i;
                }
                list.add(tmp);
            }
        }
        int[][] ans = new int[list.size()][];
        for(int i=0; i<list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
