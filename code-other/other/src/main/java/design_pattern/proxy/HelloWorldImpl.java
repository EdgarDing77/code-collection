package design_pattern.proxy;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/24
 **/
public class HelloWorldImpl implements HelloWorld {

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
