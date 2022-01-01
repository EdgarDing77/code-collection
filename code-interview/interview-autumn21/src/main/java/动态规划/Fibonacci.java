package 动态规划;

import java.util.HashMap;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/2/26
 **/
public class Fibonacci {
    HashMap<Integer, Integer> map = new HashMap<>();

    public int fibMemo(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int f;
        if (n <= 1) {
            f = n;
        } else {
            f = fibMemo(n - 1) + fibMemo(n - 2);
            map.put(n, f);
        }
        return f;
    }

    public int fibOptimized(int n) {
        if (n == 0) {
            return 0;
        }
        int prev = 0, res = 1, next;
        for (int i = 2; i <= n; i++) {
            next = prev + res;
            prev = res;
            res = next;
        }
        return res;
    }
}
