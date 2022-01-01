package written_examination_questions.sf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/30
 **/
public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] score = new int[5];
        for (int i = 0; i < 5; i++) {
            score[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        int[][] a = new int[n][5];
        int x = 0, k = 0;
        List<Integer> rec = new ArrayList<>();
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                a[i][j] = sc.nextInt();
                sum += a[i][j] * score[j];
            }
            tmp[i] = sum;
            x = Math.max(x, tmp[i]);
        }
        for (int i = 0; i < n; i++) {
            if (tmp[i] == x) {
                k++;
                rec.add(i);
            }
        }

        System.out.println(x);
        System.out.println(k);
        for (int num : rec) {
            System.out.println(num + 1);
        }


    }
}
