package written_examination_questions.xiaomi;

import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/1
 **/
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input1 = sc.nextLine();
        String num1 = sc.nextLine();
        String num2 = sc.nextLine();

        String[] input = input1.split(",");
        int m = (int) input[0].toCharArray()[2] - '0';
        int n = (int) input[1].toCharArray()[2] - '0';

        String[] nums1 = num1.split(",");
        String[] nums2 = num2.split(",");

        int len = m + n;
        int[] a = new int[len];
        int[] b = new int[n];
        for (int i = 0; i < m; i++) {
            a[i] = Integer.parseInt(nums1[i]);
        }
        for (int j = 0; j < n; j++) {
            b[j] = Integer.parseInt(nums2[j]);
        }
        merge(a, b, m);
        for (int i = 0; i < len; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void merge(int[] nums1, int[] nums2, int m) {
        int len = nums1.length;
        int n = nums2.length;
        int idx = len - 1;
        int i, j;
        for (i = m - 1, j = n - 1; i >= 0 && j >= 0; ) {
            if (nums1[i] > nums2[j]) {
                nums1[idx--] = nums1[i--];
            } else {
                nums1[idx--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[idx--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[idx--] = nums2[j--];
        }
    }
}
