package string.substring;

import java.util.HashMap;

/**
 * Description:
 * 相关子串问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/2
 **/
public class SubstringSolution {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        HashMap<Character, Integer> window = new HashMap<>();
        int res = 0;
        for (int r = 0, l = 0; r < n; r++) {
            char ch = s.charAt(r);
            window.put(ch, window.getOrDefault(ch, 0) + 1);

            while (window.get(ch) > 1) {
                char del = s.charAt(l);
                window.put(del, window.get(del) - 1);
            }
            res = Math.max(r - l + 1, res);
        }
        return res;
    }
}
