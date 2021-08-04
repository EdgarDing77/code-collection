package 动态规划;

/**
 * Description:
 * 最长递增子序列
 * 注意：子序列不一定是连续的，而子串是连续的
 *
 * @author:edgarding
 * @date:2021/2/21
 **/
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            res = Math.max(dp[j], res);
        }
        return res;
    }
}
