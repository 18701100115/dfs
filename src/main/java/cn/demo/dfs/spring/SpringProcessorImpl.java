package cn.demo.dfs.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringProcessorImpl implements BeanPostProcessor {
    //后处理bean，最重要的两步
    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        System.out.println("第五步：执行BeanPostProcessor的postProcessBeforeInitialization方法，初始化之前执行的方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        System.out.println("第八步：执行BeanPostProcessor的postProcessAfterInitialization方法，执行初始化之后的方法");
        return bean;
    }
}
