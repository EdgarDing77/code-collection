package math;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/18
 **/
public class Solution {
    /**
     * 剑指 Offer 16. 数值的整数次方
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }

    /**
     * 剑指 Offer 15. 二进制中1的个数
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        // n & (n - 1)
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 剑指 Offer 65. 不用加减乘除做加法
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     *
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        while (b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     */
    public int[] singleNumbers(int[] nums) {
        int x = 0; // 用于记录 A B 的异或结果

        /** 得到A^B的结果
         基于异或运算的以下几个性质
         1. 交换律
         2. 结合律
         3. 对于任何数x，都有x^x=0，x^0=x
         */
        for (int val : nums) {
            x ^= val;
        }

        // x & (-x)本身的作用是得到最低位的1，
        int flag = x & (-x);
        // 而我们所需要的做到的是：利用这个1来进行分组，也就是做到将A和B区分开
        // 前面已经知道，x是我们需要的结果数A和B相异或的结果，也就是说，x的二进制串上的任何一个1，都能成为区分A和B的条件
        // 因此我们只需要得到x上的任意一个1，就可以做到将A和B区分开来


        int res = 0; // 用于记录A或B其中一者

        // 分组操作
        for (int val : nums) {
            // 根据二进制位上的那个“1”进行分组
            // 需要注意的是，分组的结果必然是相同的数在相同的组，且还有一个结果数
            // 因此每组的数再与res=0一路异或下去，最终会得到那个结果数A或B
            // 且由于异或运算具有自反性，因此只需得到其中一个数即可
            if ((flag & val) != 0) {
                res ^= val;
            }
        }
        // 利用先前的x进行异或运算得到另一个，即利用自反性
        return new int[] {res, x ^ res};
    }

    /**
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        /*
        书上解法： 如果一个数字出现3次，它的二进制每一位也出现的3次。如果把所有的出现三次的数字的二进制表示的每一位都分别加起来，那么每一位都能被3整除。
        我们把数组中所有的数字的二进制表示的每一位都加起来。如果某一位能被3整除，那么这一位对只出现一次的那个数的这一肯定为0。如果某一位不能被3整除，那么只出现一次的那个数字的该位置一定为1.
         */
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] k = new int[32];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 32; j++) {
                k[j] += nums[i] & 1;
                nums[i] >>= 1;
            }
        }
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            res |= (k[i] % 3) << i;
        }
        return res;
    }

    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        // 摩尔投票
        int n = nums.length;
        if(n == 0) {
            return -1;
        }
        int vot = 0, res = nums[0];
        for(int i=0; i<n; i++) {
            if(vot == 0) {
                res = nums[i];
                vot++;
            } else if(res == nums[i]) {
                vot++;
            } else {
                vot--;
            }
        }
        return res;
    }

    /**
     * 剑指 Offer 14- I. 剪绳子
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
     * 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        /*
        任何大于1的数都可由2和3相加组成（根据奇偶证明）
        因为2*2=1*4，2*3>1*5, 所以将数字拆成2和3，能得到的积最大
        因为2*2*2<3*3, 所以3越多积越大 时间复杂度O(n/3)，用幂函数可以达到O(log(n/3)), 因为n不大，所以提升意义不大，我就没用。 空间复杂度常数复杂度O(1)
         */
        if(n <= 3) {
            return n - 1;
        }
        int div = n / 3;
        int rem = n % 3;
        long res = 1;
        for(int i=0; i<div; i++) {
            res *= i < div - 1 ? 3 : (rem == 2 ? 3 * rem : (3 + rem));
            if(res >= 1000000007) {
                res = res % 1000000007;
            }
        }
        return (int)res;
    }

    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     *
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
//        Deque<Integer> queue = new LinkedList<>();
//        for(int i=0; i<n; i++) {
//            queue.offer(i);
//        }
//        for(int i=0;queue.size() != 1; i++) {
//            if(i == m - 1) {
//                queue.poll();
//                i = -1;
//            } else {
//                queue.offer(queue.poll());
//            }
//        }
//        return queue.peek();
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        int m, n;
        if(matrix == null || (m = matrix.length) == 0 || (n = matrix[0].length) == 0) {
            return new int[]{};
        }
        int[] res = new int[m * n];
        int l = 0, r = n - 1, u = 0, d = m - 1;
        for(int index = 0; index < res.length; ) {
            for(int i=l; i<=r && index<res.length; i++) {
                res[index++] = matrix[u][i];
            }
            u++;
            for(int i=u; i<=d && index<res.length; i++) {
                res[index++] = matrix[i][r];
            }
            r--;
            for(int i=r; i>=l&&index<res.length; i--) {
                res[index++] = matrix[d][i];
            }
            d--;
            for(int i=d; i>=u&&index<res.length; i--) {
                res[index++] = matrix[i][l];
            }
            l++;
        }
        return res;
    }
}
