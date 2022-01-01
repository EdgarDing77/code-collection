package 字符串;

import java.util.HashMap;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/7/12
 **/
public class SlideWindow {
    /**
     * 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * <p>
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        if (n1 == 0 || n2 == 0) {
            return "";
        }
        HashMap<Character, Integer> needs = new HashMap<>();
        for (char ch : t.toCharArray()) {
            needs.put(ch, needs.getOrDefault(ch, 0) + 1);
        }
        HashMap<Character, Integer> window = new HashMap<>();
        int len = Integer.MAX_VALUE, valid = 0, start = 0;
        for (int left = 0, right = 0; right < n1; ) {
            char ch = s.charAt(right);
            right++;

            if (needs.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (window.get(ch).intValue() == needs.get(ch).intValue()) {
                    valid++;
                }
            }

            // 左侧窗口是否需要搜索收缩
            for (; valid == needs.size(); ) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (needs.containsKey(d)) {
                    // 注意integer会使用缓存
                    if (window.get(d).intValue() == needs.get(d).intValue()) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

}
