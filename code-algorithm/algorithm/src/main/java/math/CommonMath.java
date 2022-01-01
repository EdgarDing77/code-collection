package math;

/**
 * Description:
 * 常见的数学
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/29
 **/
public class CommonMath {


    /**
     * 最大公约数（GCD）
     * <p>
     * - 辗转相除法
     * 有两整数a和b：
     * a%b得余数c
     * 若c=0，则b即为两数的最大公约数
     * 若c≠0，则a=b，b=c，再回去执行1
     *
     * @param a
     * @param b
     * @return
     */
    public int greatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        }
        return greatestCommonDivisor(b, a % b);
    }

    /**
     * 最小公倍数(LCM)
     * <p>
     * a * b / gcd(a, b)
     *
     * @param a
     * @param b
     * @return
     */
    public int greatestCommonMultiple(int a, int b) {
        return a * b / greatestCommonDivisor(a, b);
    }

    /**
     * 完全平凡数（不使用库函数）
     * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int low = 1, high = num;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int t = num / mid;
            if (t == mid) {
                if (num % mid == 0) {
                    return true;
                }
                low = mid + 1;
            } else if (t < mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }


}
