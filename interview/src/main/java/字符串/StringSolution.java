package 字符串;

/**
 * Description:
 * 字符串的一些操作
 *
 * @author:edgarding
 * @date:2021/6/19
 **/
public class StringSolution {

    /**
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     *
     * @param str
     * @return
     */
    public String reverseWords(String str) {
        String[] strs = str.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if ("".equals(strs[i])) {
                continue;
            }
            if (i == 0) {
                sb.append(strs[i].trim());
            } else {
                sb.append(strs[i].trim()).append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    /**
     * 朴素匹配算法,找模式串t,在主串s中首次出现的位置
     *
     * @param s
     * @param t
     * @return
     */
    public int simpleKMP(String s, String t) {
        int lenS = s.length();
        int lenT = s.length();
        for (int i = 0, j = 0; i <= lenS - lenT; i++) {
            for (; j < lenT; j++) {
                if (s.charAt(i + j) != t.charAt(j)) {
                    break;
                }
            }
            if (j == lenT) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 0) {
            return "";
        } else if (n == 1) {
            return strs[0];
        }
        // 两两相交 ｜ 一起走
        String tmp = strs[0];
        for (int i = 1; i < n; i++) {
            tmp = commonPrefix(tmp, strs[i]);
            if ("".equals(tmp)) {
                return "";
            }
        }
        return tmp;
    }

    private String commonPrefix(String s1, String s2) {
        if (s1 == "" || s2 == "") {
            return "";
        }
        int index = 0;
        for (; index < s1.length() && index < s2.length() && s1.charAt(index) == s2.charAt(index); index++) {
        }
        return s1.substring(0, index);
    }

    /**
     * 字符串转换整数
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        // 有符号整数
        // 丢弃空格 -> trim()
        // 正负号 flag = true
        // 前缀0 -> 忽略
        // 整数范围 [Integer.MAX_VALUE, Integer.MIN_VALUE]
        int res = 0;
        char[] chs = s.toCharArray();
        boolean signed = true; // 正数
        int index = 0;
        for (; index < n && chs[index] == ' '; index++) {
        }
        if (index == n) {
            return 0;
        }
        if (chs[index] == '-') {
            signed = false;
            index++;
        } else if (chs[index] == '+') {
            index++;
        } else if (!Character.isDigit(chs[index])) {
            // other char
            return 0;
        }
        for (; index < n && Character.isDigit(chs[index]); index++) {
            int digit = chs[index] - '0';
            // 防止越界
            if (res > (Integer.MAX_VALUE - digit) / 10) {
                return signed ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + digit;
        }
        return signed ? res : -res;
    }
}
