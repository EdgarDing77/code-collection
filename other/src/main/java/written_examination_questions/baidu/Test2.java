package written_examination_questions.baidu;

import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/7
 **/
public class Test2 {
    public static void main(String[] args) {
        // 1 2 3
        //
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            String str = sc.nextLine();
            System.out.println(solve(str));
        }
    }

    public static String solve(String target) {
        int[] fin = new int[target.length()];
        int mark = 0;
        for (int i = 0; i < target.length(); i++) {
            if (mark == 1) {
                fin[i] = 3;
                continue;
            }
            if (target.charAt(i) >= '3') {
                fin[i] = 3;
            } else if (target.charAt(i) == '0') {
                fin[i - 1]--;
                fin[i] = 3;
                mark = 1;
            } else if (target.charAt(i) == '1') {
                fin[i] = 1;
            } else if (target.charAt(i) == '2') {
                fin[i] = 2;
            }
        }
        int st = 0;
//        StringBuilder sb = new StringBuilder();
        String ans = "";
        for (int i = 0; i < target.length(); i++) {
            if (fin[i] != 0 && st == 0) {
                st = 1;
            }
            if (st == 1) {
                ans += (char) ('0' + fin[i]);
            }
        }
        return ans;
    }

}
