package solution;

/**
 * Description:
 * 正则表达式问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/18
 **/
public class RegexSolution {

    /**
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        /*
            i , j代表s、p的索引指针
            s[i] == p[j] i + 1, j + 1

            '.' = i + 1 & j + 1

            '*' p[j - 1] == s[i] ? i++ : j++


            特例：
            p = '.*' -》 true
            s.length == 0 -> p.length % 2 == 1 ? false : continue func()
                -> p.indexOf('*') % 2 == 0 ? false : true
            p.length == 0 -> false
        */

        int n = s.length(), m = p.length();
        // 字符串长度为0，需要检验一下正则串
        if (n == 0) {
            // 如果正则长度为奇数，必定不匹配
            if (m % 2 != 0) {
                return false;
            }
            // 如果*的下标为偶数必定不对
            int i = 1;
            while (i < m) {
                if (p.charAt(i) != '*') {
                    return false;
                }
                i += 2;
            }
            return true;
        }
        // 正则串为0
        if (m == 0) {
            return false;
        }


        // 标记当前字符串和正则串的第一个元素，如果正则串长度>1，则记录正则串的后一位
        char a = s.charAt(0), b = p.charAt(0), c = 'a';
        if (m > 1) {
            c = p.charAt(1);
        }

        // 后一位
        if (c != '*') {
            // 如果没*则正常比较
            if (a == b || b == '.') {
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        } else {
            if (a == b || b == '.') {
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            } else {
                // 如果不一样这正则串两位废掉，直接往后走
                return isMatch(s, p.substring(2));
            }
        }
    }

}
