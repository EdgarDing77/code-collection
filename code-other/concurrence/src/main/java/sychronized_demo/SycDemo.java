package sychronized_demo;

/**
 * Description:
 * 以下为一些sychronized的使用方式
 * 修饰的对象有以下几种：
 * 1、代码块
 * 2、方法
 * 3、静态方法
 * 4、类
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/13
 **/
public class SycDemo {
    // 1. 锁住的为当前实例
    public synchronized void test1() {
    }

    // 2. 同步静态方法 锁住的为该类
    public synchronized static void test2() {
    }

    // 3. 与同步静态方法的锁级别一样
    public void test3() {
        synchronized (SycDemo.class) {

        }

        synchronized (this.getClass()) {

        }
    }

    // 4. 同步this实例
    public void test4() {
        synchronized (this) {

        }
    }

    // 5. 同步对象
    public static Object object = new Object();

    public void test5() {
        synchronized (object) {

        }
    }
}
