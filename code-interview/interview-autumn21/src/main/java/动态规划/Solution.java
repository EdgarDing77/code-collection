package 动态规划;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description:
 * leetcode上一些有趣的题
 *
 * @author:edgarding
 * @date:2021/6/9
 **/
public class Solution {

    /**
     * 871.盈利计划
     * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
     * 第i种工作会产生profit[i]的利润，它要求group[i]名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
     * 工作的任何至少产生minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
     * 有多少种计划可以选择？因为答案很大，所以 返回结果模10^9 + 7的值。
     * 1 <= n <= 100
     * 0 <= minProfit <= 100
     * 1 <= group.length <= 100
     * 1 <= group[i] <= 100
     * profit.length == group.length
     * 0 <= profit[i] <= 100
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        // 即一个员工只能在一时间内为一项项目工作 产生利润 -》 多种工作方式，产生不同的利润 〉 minProfilt -〉这些集合 -》 盈利计划
        // target：求计划数量 结果mod 10^9 + 7
        // case：产生的sum(profit) > minProfit
        int m = group.length;
        // restN >= group[i] 要有足够人力
        if (m == 0) {
            return 0;
        }
        // dp
        // 状态量 n -》 group[i] , minProfit -> profit, m
        // sum(profit[...i]) >= minProfit
        // 前i份工作选择了j个员工得到工作利润为minProfit的数目
        int[][][] dp = new int[m + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        final int MOD = (int) 1e9 + 7;

        for (int i = 1; i <= m; i++) {
            // 所需人数
            int people = group[i - 1];
            // 利润
            int profitNum = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j >= people) {
                        dp[i][j][k] =
                            (dp[i - 1][j - people][Math.max(k - profitNum, 0)] + dp[i - 1][j][k]) %
                                MOD;
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k] % MOD;
                    }
                }
            }
        }

        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[m][j][minProfit]) % MOD;
        }
        return sum;
    }


    /**
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return String.valueOf(nums[0]);
        }
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1 + s2).compareTo(s2 + s1);
            }
        });
        return String.join("", list);
    }
}
