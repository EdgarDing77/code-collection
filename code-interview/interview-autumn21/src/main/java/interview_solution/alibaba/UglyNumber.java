package interview_solution.alibaba;

import java.util.LinkedList;

/**
 * Description:
 * 只包含2、3、5因子的数
 * 第n项
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/11
 **/
public class UglyNumber {

    public static void main(String[] args) {
        System.out.println(findN(100));
    }

    public static int findN(int n) {
        if (n == 0) {
            return 0;
        }
        int[] res = new int[n + 1];
        LinkedList<Integer> list2 = new LinkedList<>();
        LinkedList<Integer> list3 = new LinkedList<>();
        LinkedList<Integer> list5 = new LinkedList<>();
        list2.add(2);
        list3.add(3);
        list5.add(5);
        for (int i = 1; i <= n; i++) {
            int x = min(list2.peekFirst(), list3.peekFirst(), list5.peekFirst());
            if (x == list2.peekFirst()) {
                list2.pollFirst();
            }
            if (x == list3.peekFirst()) {
                list3.pollFirst();
            }
            if (x == list5.peekFirst()) {
                list5.pollFirst();
            }
            res[i] = x;
            list2.add(x * 2);
            list3.add(x * 3);
            list5.add(x * 5);
        }
        return res[n];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
