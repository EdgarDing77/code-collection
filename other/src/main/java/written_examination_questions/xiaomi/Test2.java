package written_examination_questions.xiaomi;

import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/1
 **/
public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(getAns(n, 3));
    }

    private static int getAns(int n, int m) {
        if (n == 1) {
            return n;
        }
        return (getAns(n - 1, m) + m - 1) % n + 1;
    }
}
