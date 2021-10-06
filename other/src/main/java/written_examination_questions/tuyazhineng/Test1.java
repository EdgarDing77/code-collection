package written_examination_questions.tuyazhineng;

import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/14
 **/
public class Test1 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param str string字符串 输入字符串
     * @return string字符串
     */
    public String longestPalindrome(String str) {
        // 子串
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            String s1 = palindrome(str, i, i);
            String s2 = palindrome(str, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    public String palindrome(String str, int l, int r) {
        while (l >= 0 && r < str.length() && str.charAt(l) == str.charAt(r)) {
            r++;
            l--;
        }
        return str.substring(l + 1, r);
    }

    public static void main(String[] args) {
        String res = new Scanner(System.in).nextLine();
        System.out.println(new Test1().longestPalindrome(res));
    }
}
