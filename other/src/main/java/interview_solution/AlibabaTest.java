package interview_solution;

import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/7/30
 **/
public class AlibabaTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            // 班级数
            int n = in.nextInt();
            // 转班的学生数量
            int m = in.nextInt();
            // 通过考试人数
            double[] b = new double[n];
            // 班级人数
            double[] a = new double[n];
            for (int i = 0; i < n; i++) {
                b[i] = in.nextInt();
                a[i] = in.nextInt();
            }
            double res = 0.00000;
            // 记录班级通过率
            double[] c = new double[n];
            for (int i = 0; i < n; i++) {
                double x = 0.0;
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        x += (b[j] + m) / (a[j] + m);
                    } else {
                        x += (b[j] / a[j]);
                    }
                }
                res = Math.max(x / n, res);
            }

            System.out.printf("%.5f%n", res);
        }
    }
}
