package design_pattern.singleton;

/**
 * Description:
 * 懒汉模式 双重校验锁
 * static + 锁 双重锁检验
 * 减少了锁带来的资源开销
 * 注意实例前的volatile关键字，由于实例化一个对象需要三步
 * 1、为instance分配内存
 * 2、初始化instance
 * 3、将instance指向内存空间
 * 由于JVM具有指令重排的特性，所以以上的执行步骤有可能变为132，因此有可能对象拿到了一个未初始化的对象实例
 * 在多线程的情况下，可能会有线程在进行判断未初始化成功，进入锁代码块中重新实例化了一次，从而破坏了单例模式
 * 使用volatile修饰变量，防止指令的重排列
 * 1、解决内存可见性问题 2、防止指令重排列
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/24
 **/
public class Singleton3 {
    private static volatile Singleton3 instance;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        // 判断对象是否实例化过，没有才进入
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    // 进行实例化，这里是非原子性操作
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
