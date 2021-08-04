package 动态规划.买卖股票问题;

/**
 * Description:
 * 每天都有三种选择：
 * 1、买入 buy
 * 2、卖出 sell
 * 3、无操作 no
 * <p>
 * 三种状态：
 * 1、天数i
 * 2、允许交易的最大次数k
 * 3、当前持有的状态-「0-没持有，1-持有」
 * <p>
 * 定义：
 * dp[i][k][0/1]
 * <p>
 * base case：
 * <p>
 * dp[-1][k][0] = 0
 * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
 * dp[-1][k][1] = -infinity
 * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
 * dp[i][0][0] = 0
 * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
 * dp[i][0][1] = -infinity
 * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
 * <p>
 * 把上面的状态转移方程总结一下：
 *
 * @author:edgarding
 * @date:2021/2/24
 **/
public class MaxProfit_1 {
    /**
     * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * k = 1
     * <p>
     * <p>
     * dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
     * dp[i][1] = Math.max(dp[i - 1][1], -prices[i - 1]);
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

/*    public int maxProfit(int[] prices) {
        if(prices == null) {
            return 0;
        }
        int min = Integer.MAX_VALUE, max = 0;
        for(int i=0; i<prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i];
            }
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }*/
}
