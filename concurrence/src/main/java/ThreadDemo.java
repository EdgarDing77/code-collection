import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Description:
 * 线程创建的方式
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/13
 **/
public class ThreadDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "-thread0");
        // 第一种方式
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();
        // 第二种方式
        MyThread2 myThread2 = new MyThread2();
        new Thread(myThread2).start();
        // 第三种方式
        MyThread3 myThread3 = new MyThread3();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(myThread3);
        new Thread(futureTask).start();

    }
}

/**
 * 继承Thread
 */
class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-thread1");
    }
}

/**
 * 实现runnable接口
 */
class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-thread2");
    }
}

/**
 * 使用Callable和Future创建线程
 */
class MyThread3 implements Callable {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "-thread3");
        return 99;
    }
}
