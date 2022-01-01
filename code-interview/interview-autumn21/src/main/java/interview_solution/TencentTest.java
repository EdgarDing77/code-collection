package interview_solution;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/3/4
 **/
public class TencentTest {
    /**
     * 题目：给定两个字符串 s 和 t，其中 t 是 s 的子字符串。t的子字符串是字符都取自s，并且保持在s中的位置的相对顺序，但不需要是连续的。比如，s="abcdef"，t="bd"。
     * 要求找出 t 在 s 中匹配的子字符串数量。
     */
    int res = 0;

    public int getNum(String s, String t) {
        // t是s的子字符串
        // 求：数量（子序列）
        make(s, 0, t, 0);
        return res;
    }

    public void make(String s, int sStart, String t, int tStart) {
        if (sStart < s.length() && tStart < t.length()) {
            for (int i = sStart; i < s.length(); i++) {
                if (s.charAt(i) == t.charAt(tStart)) {
                    if (tStart + 1 == t.length()) {
                        ++res;
                    } else {
                        make(s, sStart + 1, t, tStart + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "abcdefbdbdb", t = "bd";
        TencentTest test = new TencentTest();
        System.out.println(test.getNum(s, t));
    }
}
