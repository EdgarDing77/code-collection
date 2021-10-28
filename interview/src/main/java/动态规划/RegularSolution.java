package 动态规划;

import java.util.HashMap;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/26
 **/
public class RegularSolution {
    /**
     * 剑指 Offer 19. 正则表达式匹配
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。
     * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // 大致思路：双指针移动，若都移动到末尾，则匹配成功
        return dp(s, 0, p, 0, new HashMap<>());
    }

    private boolean dp(String s, int i, String p, int j, HashMap<String, Boolean> memo) {
        int m = s.length(), n = p.length();
        if (j == n) {
            return i == m;
        }
        if (i == m) {
            // *的位置
            if ((n - j) % 2 == 1) {
                return false;
            }
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }
        String key = String.valueOf(i + "," + j);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res = dp(s, i, p, j + 2, memo) || dp(s, i + 1, p, j, memo);
            } else {
                res = dp(s, i + 1, p, j + 1, memo);
            }
        } else {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res = dp(s, i, p, j + 2, memo);
            } else {
                res = false;
            }
        }
        memo.put(key, res);
        return res;
    }
}
