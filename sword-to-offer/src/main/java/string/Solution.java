package string;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/30
 **/
public class Solution {
    /**
     * 剑指 Offer 05. 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                sb.append("%20");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        //取出头尾的空格
        String str = s.trim();
        String[] string = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = string.length - 1; i >= 0; i--) {
            if (string[i].equals(" ") || string[i].equals("")) {
                continue;
            }
            sb.append(string[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * <p>
     * 数值（按顺序）可以分成以下几个部分：
     * <p>
     * 若干空格
     * 一个 小数 或者 整数
     * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
     * 若干空格
     * 小数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 下述格式之一：
     * 至少一位数字，后面跟着一个点 '.'
     * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 一个点 '.' ，后面跟着至少一位数字
     * 整数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 至少一位数字
     * 部分数值列举如下：
     * <p>
     * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
     * 部分非数值列举如下：
     * <p>
     * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if (s == null && s.length() == 0) {
            return false;
        }
        s = s.trim();
        char[] chs = s.toCharArray();
        boolean numFlag = false, dotFlag = false, eFlag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numFlag = true;
            } else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                dotFlag = true;
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !eFlag && numFlag) {
                eFlag = true;
                numFlag = false;
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') &&
                (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {

            } else {
                return false;
            }
        }
        return numFlag;
    }

    /**
     * 剑指 Offer 67. 把字符串转换成整数
     * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * <p>
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * <p>
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     * <p>
     * 说明：
     * <p>
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *
     * @param str
     * @return
     */
    public int strToInt(String str) {
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 0, sign = 1, length = str.length();
        if (length == 0) {
            return 0;
        }
        while (str.charAt(i) == ' ') {
            if (++i == length) {
                return 0;
            }
        }
        if (str.charAt(i) == '-') {
            sign = -1;
        }
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            i++;
        }
        for (int j = i; j < length; j++) {
            if (str.charAt(j) < '0' || str.charAt(j) > '9') {
                break;
            }
            if (res > bndry || res == bndry && str.charAt(j) > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (str.charAt(j) - '0');
        }
        return sign * res;
    }

    /**
     * 剑指 Offer 38. 字符串的排列
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * <p>
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        char[] chars = new char[s.length()];
        Set<String> set = new HashSet<>();
        boolean[] isViewed = new boolean[s.length()];

        dfs(s, 0, chars, isViewed, set);
        return set.toArray(new String[0]);
    }

    private void dfs(String s, int index, char[] chars, boolean[] isViewed, Set<String> set) {
        if (index == s.length()) {
            set.add(new String(chars));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isViewed[i]) {
                continue;
            }
            isViewed[i] = true;
            chars[index] = s.charAt(i);
            dfs(s, index + 1, chars, isViewed, set);
            isViewed[i] = false;
        }
    }
}
