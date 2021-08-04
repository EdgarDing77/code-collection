package MyThreadPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/7/30
 **/
public class MyThreadPoolExecutor {
    private static final Object o = new Object();
    private static BlockingQueue<Runnable> workQueue = null;// 任务阻塞队列
    private final HashSet<Worker> workerSet = new HashSet<>();// 不重复的工作集
    private final ArrayList<Thread> threadFactory = new ArrayList<>();// 线程工厂
    private volatile boolean RUNNING = true;// 是否正在运行
    private volatile int coreSize;// 线程池的核心线程数
    private volatile int curSize;// 当前线程池中的线程数
    private volatile boolean shutdown = false;// 是否停止工作

    public MyThreadPoolExecutor(int coreSize) {
        this.coreSize = coreSize;
        workQueue = new ArrayBlockingQueue<>(coreSize);
    }

    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }

        if (curSize < coreSize) {
            addThread(command);
        } else {
            try {
                // 线程数不够，放入阻塞队列
                workQueue.put(command);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void addThread(Runnable task) {
        synchronized (o) {
            try {
                curSize++;
                Worker worker = new Worker(task);
                workerSet.add(worker);
                Thread thread = new Thread(worker);
                threadFactory.add(thread);
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void shutdown() {
        RUNNING = false;
        if (!workerSet.isEmpty()) {
            for (Worker worker : workerSet) {
                worker.interruptIfIdle();
            }
        }
        shutdown = true;
        Thread.currentThread().interrupt();
    }

    private final class Worker implements Runnable {
        private Runnable task;

        public Worker(Runnable task) {
            workQueue.offer(task);
        }

        public Runnable getTask() throws InterruptedException {
            return workQueue.take();
        }

        @Override
        public void run() {
            while (RUNNING) {
                if (shutdown) {
                    Thread.interrupted();
                }
                try {
                    task = getTask();
                    task.run();
                } catch (InterruptedException ignored) {

                }
            }
        }

        public void interruptIfIdle() {
            for (Thread thread : threadFactory) {
                System.out.println(thread.getName() + " interrupt");
                thread.interrupt();
            }
        }
    }
}
