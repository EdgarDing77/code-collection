package search_algorithem;

/**
 * Description:
 * 中等难度
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/6
 **/
public class MediumSolution {
    /**
     * 剑指 Offer 04. 二维数组中的查找
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length, n;
        if (m == 0 || (n = matrix[0].length) == 0) {
            return false;
        }
        int i = 0, j = n - 1;
        for (; i < m && j >= 0; ) {
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int n;
        if ((n = numbers.length) == 0) {
            return -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            } else if (numbers[mid] < numbers[r]) {
                r = mid;
            } else {
                r--;
            }
        }
        return numbers[l];
    }

    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        int n = s.length();
        if (n == 0) {
            return ' ';
        }
        int[] rec = new int[26];
        for (char ch : s.toCharArray()) {
            rec[ch - 'a']++;
        }
        for (char ch : s.toCharArray()) {
            if (rec[ch - 'a'] == 1) {
                return ch;
            }
        }
        return ' ';
    }

}
