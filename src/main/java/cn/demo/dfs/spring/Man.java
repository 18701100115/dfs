package cn.demo.dfs.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Man implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {
    @Value("小胖猪")
    private String name;

    public Man() {
        System.out.println("第一步：实例化类，执行构造方法new");
    }
    @PostConstruct
    public void init() {
        System.out.println("执行对象有PostConstruct注解的方法，name="+name);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("第三步：执行继承BeanNameAware的setBeanName 设置bean的名称也就是spring容器中的名称，也就是id值 " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("第四步：执行继承ApplicationContextAware的setApplicationContext方法了解工厂信息ApplicationContext");
    }
    //第五步执行初始化之前执行的方法


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第六步：执行继承InitializingBean的afterPropertiesSet方法 属性设置后执行的方法");
    }

    public void setup() {
        System.out.println("第七步：执行自己配置的初始化方法");
    }
    //第八步执行初始化之后执行的方法
    public void run() {
        System.out.println("第九步：执行自身的业务方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("第十步：调用AnnotationConfigApplicationContext.close方法时执行，执行spring的销毁方法");
    }
    @PreDestroy
    public void PreDestroy() {
        System.out.println("第十一步： @PreDestroy 在destroy之前执行，执行自己配置的销毁方法");
    }


}