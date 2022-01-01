package 递归;

/**
 * Description:
 * 斐波那契数列
 * f(n)=f(n-1)+f(n-2)
 * 1，1，2，3，5，8
 *
 * @author:edgarding
 * @date:2021/6/7
 **/
public class Fibonacci {
    /**
     * 求第n个斐波那契数
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int fib2(int n) {
        if (n == 2 || n == 1) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
}
