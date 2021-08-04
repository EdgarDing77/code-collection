package jvm;

/**
 * Description:
 * 方法静态分配演示
 *
 * @author:edgarding
 * @date:2020/11/6
 **/
public class StaticDispatch {
    static class Human {

    }

    static class Woman extends Human {
    }

    static class Man extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("this is human say");
    }

    public void sayHello(Woman guy) {
        System.out.println("this is woman say");
    }

    public void sayHello(Man guy) {
        System.out.println("this is man say");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
