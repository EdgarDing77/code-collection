package written_examination_questions.baidu;

import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/7
 **/
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] pic = new int[N][N];
        int[][] res = new int[N * K][N * K];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pic[i][j] = sc.nextInt();
                int val = pic[i][j];
                for (int x = 0; x < K; x++) {
                    for (int y = 0; y < K; y++) {
                        res[i * K + x][j * K + y] = val;
                    }
                }
            }
        }

        for (int i = 0; i < N * K; i++) {
            for (int j = 0; j < N * K; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
