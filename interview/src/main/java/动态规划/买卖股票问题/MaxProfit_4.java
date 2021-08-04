package 动态规划.买卖股票问题;

/**
 * Description:
 * k = infinity
 * 类似于k = 2的问题
 *
 * @author:edgarding
 * @date:2021/2/24
 **/
public class MaxProfit_4 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        // k 剩余买卖次数
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
