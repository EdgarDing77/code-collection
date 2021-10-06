package lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/10
 **/
public class TestLockDemo {

    private static final Object object = new Object();

    public synchronized void test() {
        // try to get object
    }

    public void test2() {
        // boolean isFair -> default is false(no fair)
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            // try to get object
        } finally {
            reentrantLock.unlock();
        }
    }

    public void test3() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}
