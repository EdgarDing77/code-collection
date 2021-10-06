package design_pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/24
 **/
public class JDKProxyTest {
    public static void main(String[] args) {
        HelloWorld helloWorld = (HelloWorld) Proxy.
            newProxyInstance(JDKProxyTest.class.getClassLoader(),
                new Class<?>[] {HelloWorld.class},
                new MyInvocationHandler(new HelloWorldImpl()));
        helloWorld.sayHello();
    }
}
