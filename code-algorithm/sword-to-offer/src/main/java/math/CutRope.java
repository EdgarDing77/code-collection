package math;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/28
 **/
public class CutRope {
    /**
     * 剑指 Offer 14- I. 剪绳子
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
     * 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * @param n
     * @return
     */
    public int cuttingRope1(int n) {
        /*
        任何大于1的数都可由2和3相加组成（根据奇偶证明）
        因为2*2=1*4，2*3>1*5, 所以将数字拆成2和3，能得到的积最大
        因为2*2*2<3*3, 所以3越多积越大 时间复杂度O(n/3)，用幂函数可以达到O(log(n/3)), 因为n不大，所以提升意义不大，我就没用。 空间复杂度常数复杂度O(1)
         */
        if (n <= 3) {
            return n - 1;
        }
        int div = n / 3;
        int rem = n % 3;
        long res = 1;
        for (int i = 0; i < div; i++) {
            res *= i < div - 1 ? 3 : (rem == 2 ? 3 * rem : (3 + rem));
            if (res >= 1000000007) {
                res = res % 1000000007;
            }
        }
        return (int) res;
    }

    /**
     * 剑指 Offer 14- II. 剪绳子 II
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * @param n
     * @return
     */
    public int cuttingRope2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        long res = 1;
        while (n > 4) {
            res *= 3;
            res = res % 1000000007;
            n -= 3;
        }
        return (int) (res * n % 1000000007);
    }
}
