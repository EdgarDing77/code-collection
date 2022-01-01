package written_examination_questions.baidu;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/7
 **/
public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String str = sc.nextLine();
        char[] chs = str.toCharArray();

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }
        int res = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {

                if (set.contains(chs[i])) {

                }
            }
        }


    }
}
