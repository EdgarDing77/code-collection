package written_examination_questions.weripinghui;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/15
 **/
public class Test2 {

    public int maxProfit(int[] prices) {
        // write code here
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
}
