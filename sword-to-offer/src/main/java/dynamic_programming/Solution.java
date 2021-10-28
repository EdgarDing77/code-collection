package dynamic_programming;

import java.util.HashSet;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/6
 **/
public class Solution {
    /**
     * 剑指 Offer 10- I. 斐波那契数列
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        // 0 1 1 2 3
        if (n < 2) {
            return n;
        }
        int a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        // 0 - 1 1 - 1 2 - 2
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    /**
     * 剑指 Offer 63. 股票的最大利润
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i - 1]);
        }
        return dp[n][0];
    }

    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * <p>
     * 要求时间复杂度为O(n)。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // 向后连接｜重新开始
        int n;
        if ((n = nums.length) == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            res = Math.max(dp[i], res);
        }
        return res;
    }

    /**
     * 剑指 Offer 47. 礼物的最大价值
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n;
        if (m == 0 || (n = grid[0].length) == 0) {
            return 0;
        }
//        int[][] dp = new int[m+1][n+1];
//        for(int i=1; i<=m; i++) {
//            for(int j=1; j<=n; j++) {
//                dp[i][j] = Math.max(dp[i-1][j] + grid[i-1][j-1], dp[i][j-1] + grid[i-1][j-1]);
//            }
//        }
//        return dp[m][n];
        // dp优化
        int[] dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[j] = Math.max(dp[j] + grid[i][j - 1], dp[j - 1] + grid[i][j - 1]);
            }
        }
        return dp[n];
    }

    /**
     * 剑指 Offer 46. 把数字翻译成字符串
     * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
     * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= str.length(); i++) {
            String tmpStr = str.substring(i - 2, i);
            if (tmpStr.compareTo("10") >= 0 && tmpStr.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }

    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }
        char[] chs = s.toCharArray();
        int l = 0, r = 0;
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        while (r < chs.length) {
            char ch = chs[r++];
            while (set.contains(ch)) {
                set.remove(chs[l++]);
            }
            set.add(ch);
            res = Math.max(res, set.size());
        }
        return res;
    }


}
