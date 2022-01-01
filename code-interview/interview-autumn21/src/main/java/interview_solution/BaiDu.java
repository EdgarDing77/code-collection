package interview_solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/7/19
 **/
public class BaiDu {
    // 大小写全排列
    // S = "a1B2"
    // A1b2 a1B2 B2a
    public static List<String> solution(String str) {
        List<String> res = new ArrayList<>();
        char[] chars = str.toCharArray();
        dfs(chars, 0, res);
        return res;
    }

    private static void dfs(char[] chars, int index, List<String> res) {
        if (index == chars.length) {
            res.add(new String(chars));
            return;
        }
        dfs(chars, index + 1, res);
        if (Character.isLetter(chars[index])) {
            // 变化大小写
            chars[index] ^= 1 << 5;
            dfs(chars, index + 1, res);
        }
    }

    public static void main(String[] args) {
        String s = "a1B2";
        List<String> res = solution(s);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
