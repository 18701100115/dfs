//package cn.demo.dfs.spring;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class B implements ApplicationContextAware {
////@Autowired
////A a;
//    public B() {
//        System.out.println("========new B()");
//    }
//    @Autowired
//    public void construct() {    //通过@PostConstruct的必须执行，把它假装看成一个构造函数
//        System.out.println("============243234324");
//    }
//    @PostConstruct
//    public void init(){
//        System.out.println("============init");
//    }
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println("========setApplicationContext;");
//    }
//}
