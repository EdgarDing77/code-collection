package math;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/18
 **/
public class Solution {


    /**
     * 剑指 Offer 51. 数组中的逆序对
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组，求出这个数组中的逆序对的总数。
     *
     * @param nums
     * @return
     */
    int count = 0;

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
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        // 摩尔投票
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        int vot = 0, res = nums[0];
        for (int i = 0; i < n; i++) {
            if (vot == 0) {
                res = nums[i];
                vot++;
            } else if (res == nums[i]) {
                vot++;
            } else {
                vot--;
            }
        }
        return res;
    }

    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     * <p>
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     *
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
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        int m, n;
        if (matrix == null || (m = matrix.length) == 0 || (n = matrix[0].length) == 0) {
            return new int[] {};
        }
        int[] res = new int[m * n];
        int l = 0, r = n - 1, u = 0, d = m - 1;
        for (int index = 0; index < res.length; ) {
            for (int i = l; i <= r && index < res.length; i++) {
                res[index++] = matrix[u][i];
            }
            u++;
            for (int i = u; i <= d && index < res.length; i++) {
                res[index++] = matrix[i][r];
            }
            r--;
            for (int i = r; i >= l && index < res.length; i--) {
                res[index++] = matrix[d][i];
            }
            d--;
            for (int i = d; i >= u && index < res.length; i--) {
                res[index++] = matrix[i][l];
            }
            l++;
        }
        return res;
    }

    /**
     * 剑指 Offer 49. 丑数
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return -1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int id2 = 0, id3 = 0, id5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[id2] * 2, Math.min(dp[id3] * 3, dp[id5] * 5));
            if (dp[id2] * 2 == dp[i]) {
                id2 += 1;
            }
            if (dp[id3] * 3 == dp[i]) {
                id3 += 1;
            }
            if (dp[id5] * 5 == dp[i]) {
                id5 += 1;
            }
        }
        return dp[n - 1];
    }

    /**
     * 剑指 Offer 60. n个骰子的点数
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * <p>
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        double[] ans = new double[5 * n + 1];
        double all = Math.pow(6, n);
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= 6 * n; j++) {
                for (int k = 1; k <= 6; k++) {
                    dp[i][j] += j >= k ? dp[i - 1][j - k] : 0;
                    if (i == n) {
                        ans[j - i] = dp[i][j] / all;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int[] res = new int[(int) Math.pow(10, n) - 1];
        for (int i = 1; i <= res.length; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;//不构成任何一对
        }
        mergesort(nums, 0, len - 1);
        return count;
    }

    private int[] mergesort(int[] nums, int l, int r) {
        if (l >= r) {
            return new int[] {nums[l]};
        }
        int mid = l + ((r - l) >> 1);
        int[] left = mergesort(nums, l, mid);
        int[] right = mergesort(nums, mid + 1, r);
        int[] res = new int[r - l + 1];
        int i = 0, j = 0, index = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                res[index++] = left[i++];
            } else {
                res[index++] = right[j++];
                count += left.length - i;
            }
        }
        while (i < left.length) {
            res[index++] = left[i++];
        }
        while (j < right.length) {
            res[index++] = right[j++];
        }
        return res;
    }

    /**
     * 剑指 Offer 43. 1～n 整数中 1 出现的次数
     * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
     * <p>
     * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                res += high * digit;
            } else if (cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    /**
     * 剑指 Offer 44. 数字序列中某一位的数字
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
     * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
     * <p>
     * 请写一个函数，求任意第n位对应的数字。
     * 数字范围    数量  位数    占多少位
     * 1-9        9      1       9
     * 10-99      90     2       180
     * 100-999    900    3       2700
     * 1000-9999  9000   4       36000  ...
     * <p>
     * 例如 2901 = 9 + 180 + 2700 + 12 即一定是4位数,第12位   n = 12;
     * 数据为 = 1000 + (12 - 1)/ 4  = 1000 + 2 = 1002
     * 定位1002中的位置 = (n - 1) %  4 = 3    s.charAt(3) = 2;
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int digit = 1; // 位数
        long start = 1; // 开始范围的第一个数
        long count = 9; // 多少位
        while (n > count) {
            n -= count;
            digit++;
            start *= 10;
            count = digit * start * 9;
        }
        // n-1 减去最开头的0
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
}
