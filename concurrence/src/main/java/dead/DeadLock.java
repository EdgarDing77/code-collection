package dead;

/**
 * Description:
 * 死锁
 *
 * @author:edgarding
 * @date:2021/7/18
 **/
public class DeadLock {
    private static Object object1 = new Object();
    private static Object object2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (object1) {
                try {
                    System.out.println(Thread.currentThread().getName() + "正在持有资源1");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println(Thread.currentThread().getName() + "占有资源2");
                }
            }
            System.out.println("释放资源1");
        }, "线程1").start();

        new Thread(() -> {
            synchronized (object2) {
                try {
                    System.out.println(Thread.currentThread().getName() + "正在持有资源2");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println(Thread.currentThread().getName() + "占有资源1");
                }
            }
            System.out.println("释放资源2");
        }, "线程2").start();
    }
}
