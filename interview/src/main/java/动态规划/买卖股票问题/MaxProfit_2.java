package 动态规划.买卖股票问题;

/**
 * Description:
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 思路：
 * k = infinity问题
 * 可与k = 1的问题一样
 * <p>
 * <p>
 * dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
 * dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
 *
 * @author:edgarding
 * @date:2021/2/24
 **/
public class MaxProfit_2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
        }
        return dp[n][0];
    }
}
