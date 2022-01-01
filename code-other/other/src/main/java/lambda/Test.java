package lambda;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/27
 **/
public class Test {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + " " + " thsifdsafdsa");
        new Thread(() -> {

        }).start();
    }
}
