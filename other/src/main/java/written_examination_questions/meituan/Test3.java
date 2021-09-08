package written_examination_questions.meituan;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Description:
 * 小美最近在课外书上发现一篇文章，文章中描述了一种奇怪的序列，名字叫做"阔浩"序列。
 * <p>
 * "阔浩"序列是这样定义的：空串是合法的"阔浩"序列，且它拥有1的代价。
 * <p>
 * 如果s是合法的"阔浩"序列，那么(s)也是合法的"阔浩"序列，且(s)的代价为s的代价加一
 * <p>
 * 如果s，t都是合法的"阔浩"序列，那么它们拼接起来的st也是合法的"阔浩"序列，且总的代价为s的代价乘以t的代价。
 * <p>
 * 例如，(()())() 是合法的"阔浩"序列，而 )(())(() 不是合法的"阔浩"序列，且前者的代价为(2*2+1)*2=10
 * <p>
 * <p>
 * <p>
 * 输入描述
 * 一行仅包含“ ( ”   和  “ ) ”  的合法"阔浩"序列。（不含引号）
 * <p>
 * 100%的数据保证，"阔浩"序列长度len满足1 <= len <= 200,000。
 * <p>
 * 输出描述
 * 输出一个数，"阔浩"序列的代价。由于答案可能过大，所以输出模意义下的"阔浩"序列的代价。
 * <p>
 * 形式化的，设代价为ans，输出ans%P（P=1,000,000,007）。其中，%为模运算，用于求解余数，例如5%2=1，9%7=2。
 * <p>
 * <p>
 * 样例输入
 * ()()()(())
 * 样例输出
 * 24
 * <p>
 * 提示
 * 前三个()，每个单独代价都是2
 * <p>
 * 它们并列之后代价为乘积8
 * <p>
 * 而最后一组(())单独代价为3
 * <p>
 * 与前面并列的乘起来最终代价为24
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/22
 **/
public class Test3 {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int res = ans(input);
        System.out.println(res % MOD);
    }

    public static int ans(String str) {
        if (" ".equals(str)) {
            return 1;
        }
        Deque<Character> stack = new ArrayDeque<>();
        char[] chs = str.toCharArray();
        int tmp = 0;
        int res = 1;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                stack.push(chs[i]);
            } else if (chs[i] == ')') {
                if (stack.isEmpty()) {
                    continue;
                }
                stack.pop();
                tmp += 1;
            }
            if (i != 0 && stack.isEmpty()) {
                res = ((tmp + 1) * res) % MOD;
                tmp = 0;
            }
        }
        return res;
    }
}
