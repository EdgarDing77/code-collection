package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * N数之和
 *
 * @author:edgarding
 * @date:2021/6/25
 **/
public class NSum {
    /**
     * 最接近的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;
        // 枚举 a
        for (int i = 0; i < n - 2; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1, k = n - 1; j < k; ) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    while (j < k && nums[k - 1] == nums[k]) {
                        --k;
                    }
                    k--;
                } else {
                    while (j < k && nums[j + 1] == nums[j]) {
                        ++j;
                    }
                    j++;
                }
            }
        }
        return best;
    }


    /**
     * 三数之和为0
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            // 如果nums[i] > 0了，数组后面就不可能为0
            if (nums[i] > 0) {
                return res;
            }
            // 去重操作！！
            if (i != 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            // 目标数为它的负数
            int target = -nums[i];
            // j为mid指针，k为right指针，进行移动
            for (int j = i + 1, k = n - 1; j < k; ) {
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // ！去重复操作，这是关键
                    while (j < k && nums[j + 1] == nums[j]) {
                        j++;
                    }
                    while (j < k && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }

    /**
     * N数之和
     *
     * @param nums
     * @param n
     * @param target
     * @return
     */
    public List<List<Integer>> nSum(int[] nums, int n, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        return nSumTarget(nums, n, 0, target);
    }

    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        int size = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 最少是n>2的情况
        if (n < 2 || size < n) {
            return res;
        }
        if (n == 2) {
            int lo = start, hi = size - 1;
            while (lo < hi) {
                List<Integer> ans = new ArrayList<>();
                int left = nums[lo], right = nums[hi];
                int sum = left + right;
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                } else if (sum == target) {
                    ans.add(left);
                    ans.add(right);
                    res.add(ans);
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                }
            }
        } else {
            for (int i = start; i < size; i++) {
                List<List<Integer>> ret = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : ret) {
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < size - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }


}
