package top.edgarding.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.edgarding.aop.annotation.LogTest;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/12/5
 **/
@RestController
public class DemoController {

    @GetMapping("/test")
    @LogTest("testDemo()注解成功！")
    public void testDemo() {
        System.out.println("testDemo()方法执行成功！");
    }
}
