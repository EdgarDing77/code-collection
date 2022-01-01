package design_pattern.singleton;

/**
 * Description:
 * 饿汉模式
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/24
 **/
public class Singleton4 {
    private static Singleton4 instance = new Singleton4();

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        return instance;
    }
}
