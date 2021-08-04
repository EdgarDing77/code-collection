package jvm;

/**
 * Description:
 * 针对自动装箱、拆箱的问题
 *
 * @author:edgarding
 * @date:2020/11/28
 **/
public class AutoTransfer {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 128;
        Integer f = 128;
        Long g = 3L;
        System.out.println("c == d:" + (c == d));
        System.out.println("e == f:" + (e == f));
        System.out.println("c == (a + b):" + (c == (a + b)));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }
}
