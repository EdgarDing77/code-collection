package springframework.simple_bean;

/**
 * Description:
 * 简单Bean封装
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/5
 **/
public class BeanDefinition {
    private Object object;

    public BeanDefinition(Object object) {
        this.object = object;
    }

    public Object getBean() {
        return object;
    }
}
