package dynamic_programming.subsequence;

import java.util.HashMap;

/**
 * Description:
 * 子序列问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/5
 **/
public class SubsequenceSolution {
    /**
     * 最长定差子序列
     * 给你一个整数数组arr和一个整数difference，请你找出并返回 arr中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
     * <p>
     * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int v : arr) {
            map.put(v, map.getOrDefault(v - difference, 0) + 1);
            res = Math.max(res, map.get(v));
        }
        return res;
    }
}
