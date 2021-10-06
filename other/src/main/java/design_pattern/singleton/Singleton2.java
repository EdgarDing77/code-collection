package design_pattern.singleton;

/**
 * Description:
 * 懒汉模式 - 线程安全 效率低
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/24
 **/
public class Singleton2 {
    private static Singleton2 instance = null;

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
