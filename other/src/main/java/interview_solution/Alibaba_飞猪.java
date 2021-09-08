package interview_solution;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/8/17
 **/
public class Alibaba_飞猪 {
    public static void main(String[] args) {
        short a = 128;
        byte b = (byte) a;
        System.out.println(b);
        new B();
    }
}

class A extends Object {
    static {
        System.out.println("load A");
    }

    public A() {
        System.out.println("create A");
    }
}

class B extends A {
    static {
        System.out.println("load B");
    }

    public B() {
        System.out.println("create B");
    }
}
