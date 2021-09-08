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
public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int w = sc.nextInt();
            int[] a = new int[n];
            boolean[] isVis = new boolean[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            Arrays.sort(a);
            // n个人 w载重上线
            // 最值 dp
            System.out.println(solve(a, w, isVis));
        }
    }

    public static int solve(int[] arr, int w, boolean[] isVis) {
        if (arr.length == 1) {
            return 1;
        }
        int sum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (isVis[i]) {
                continue;
            }
            sum++;
            for (int j = 0; j < i; j++) {
                if (isVis[j]) {
                    continue;
                }
                if ((arr[i] + arr[j] <= w) && (arr[i] + arr[j]) % 2 == 0) {
                    isVis[j] = true;
                    break;
                }
            }
        }
        return sum;
    }
}
