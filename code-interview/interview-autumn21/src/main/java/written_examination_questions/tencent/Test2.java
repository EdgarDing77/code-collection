package written_examination_questions.tencent;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/22
 **/
public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        final long mod = 1000000007;
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            Arrays.sort(a);
            long ans = 0;
            for (int i = n - 1; i >= 0; i--) {
                a[i] = (int) ((a[i] + ans) % mod);
                ans = (ans + a[i]) % mod;
            }
            System.out.println(ans);
        }
    }
}
