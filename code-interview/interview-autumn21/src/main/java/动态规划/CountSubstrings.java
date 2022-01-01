package 动态规划;

/**
 * Description:
 * 计算这个字符串中有多少个回文子串
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * <p>
 * 思路：在计算回文串的时候，首先想到从中间向两边扩散（注意奇数长度和偶数长度的区别）
 *
 * @author:edgarding
 * @date:2021/2/24
 **/
public class CountSubstrings {
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += count(s, i, i); // 奇数
            res += count(s, i, i + 1); //偶数
        }
        return res;
    }

    private int count(String s, int start, int end) {
        int num = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            num++;
        }
        return num;
    }
}
