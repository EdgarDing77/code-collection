package top.edgarding.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import top.edgarding.aop.annotation.LogTest;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/12/5
 **/
@Aspect
@Component
public class AopDemoAspect {

    @Around("@within(logTest) || @annotation(logTest)")
    public Object doAopTest(ProceedingJoinPoint point, LogTest logTest) throws Throwable {
        if (logTest == null) {
            logTest = point.getTarget().getClass().getAnnotation(LogTest.class);
        }
        if (logTest == null) {
            System.out.println("未找到该注解！");
        }
        String val = logTest.value();
        System.out.println("around: " + val);
        return point.proceed();
    }
}
