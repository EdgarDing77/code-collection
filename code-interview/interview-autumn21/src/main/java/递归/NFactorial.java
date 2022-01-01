package 递归;

/**
 * Description:
 * 求阶乘n！
 * 阶乘
 *
 * @author:edgarding
 * @date:2021/6/7
 **/
public class NFactorial {

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

}
