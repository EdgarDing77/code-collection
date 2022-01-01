package 动态规划.买卖股票问题;

/**
 * Description:
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 * <p>
 * k = 2
 *
 * @author:edgarding
 * @date:2021/2/24
 **/
public class MaxProfit_3 {
    public int maxProfit(int[] prices) {
        // k = 2;
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        // k 剩余买卖次数
        int k = 2;
        int[][][] dp = new int[n + 1][k + 1][2];
        for (int i = k; i >= 1; i--) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= 1; j--) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
            }
        }
        return dp[n][k][0];
    }
}
