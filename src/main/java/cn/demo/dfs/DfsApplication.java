package cn.demo.dfs;

import cn.demo.dfs.config.AppConfig;
//import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

@SpringBootApplication
//@EnableDubboConfiguration
//@DependsOn
public class DfsApplication{

//    @Bean
//    public ConstantMonitor getBean(){
//    return new ConstantMonitor();
//}
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//        ac.register(AppConfig.class);//注册AppConfig
//        ac.refresh();//解析AppConfig里的ComponentScan注入的包名进行实例化对象 new
//        1.实例化spring容器
//        2.扫描类
//        3.解析这个类
//        4.实例化成BeanDefinition
//        5.所有的BeanDefinition存到Map中
//        6.调用Bean工厂的后置处理器
//        7.对象实例化验证
//        8.推断构造方法
//        9.new 对象(反射)
//        10.缓存注解信息(解析合并后BeanDefinition对象)  getMergedLocalBeanDefinition
//        11.提前暴露自己对象
//        12.判断是否需要完成属性注入。如果需要则进行属性注入
//        13.调用所有的Aware
//        14.调用生命周期回调方法
//        14.完成代理(AOP)
//        15.PUT到容器中
//        16.销毁这个对象 （当调用stop或者close方法的时候）


        //调用refresh.finishBeanFactoryInitialization的时候开始进行实例化(8-15步)
        // finishBeanFactoryInitialization.preInstantiateSingletons取到所有beanNames循环调用getBean方法进行实例化对象
        //getBean钓友transformedBeanName方法来验证bean的名字是否合法
        // 调用getSingleton判断beanName是否存在，如果不存在放入容器(单例池，只初始化一次，重复使用)，如何存在，直接返回对象， singletonObjects对象里面--Map类型 注：spring存储的都为单例Bean，原型Bean不进行存储
        //getMergedLocalBeanDefinition合并BeanDefinition对象
        //判断有没有DependsOn注解(加载依赖) 如果有先处理DependsOn的Bean实例化
        //判断是否是单例 如果是调用createBean方法
        //调用doCreateBean方法
        //determineConstructorsFromBeanPostProcessors 后置处理器推断构造方法，类的注入属性Bean
        //调用instantiateBean方法
        //通过                            constructorToUse = clazz.getDeclaredConstructor();得到默认构造方法
        //调用BeanUtils.instantiateClass 调用 ctor.newInstance(argsWithDefaultValues)完成实例化 放到BeanWrapper
        //applyMergedBeanDefinitionPostProcessors注解属性存到集合中@Autowired
        //populateBean 判断是否需要完成属性注入。如果需要则进行属性注入

    //PostConstruct注解在对象实例化执行完构造方法完成属性注入之后执行
    //调用invokeAwareMethods方法和applyBeanPostProcessorsBeforeInitialization方法
    //执行      @PostConstruct 注解的方法
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(DfsApplication.class, args);
    }

}
