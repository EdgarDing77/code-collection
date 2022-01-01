package written_examination_questions.sf;

import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/30
 **/
public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] input = new int[n * 3];
        int o = 0, p = 0;
        for (int i = 0; i < input.length; i++) {
            input[i] = scanner.nextInt();
            if (input[i] % 2 == 0) {
                o++;
            } else {
                p++;
            }
        }
        int res = 0;
        // 奇数 -》 偶偶奇｜奇奇奇
        for (int i = 0; i < n; i++) {
            if (o >= 2 && p >= 1) {
                o -= 2;
                p -= 1;
                res++;
            } else if (p >= 3) {
                p -= 3;
                res++;
            }
        }
        System.out.println(res);
    }

}
