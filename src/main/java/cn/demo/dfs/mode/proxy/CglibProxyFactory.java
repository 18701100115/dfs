package cn.demo.dfs.mode.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Cglib代理
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object object;

    public CglibProxyFactory(Object object) {
        this.object = object;
    }
    public Object getProxyInstance(){
        Enhancer en = new Enhancer();
        en.setSuperclass(object.getClass());
        en.setCallback(this);
        return en.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务");
                  Object returnValue = method.invoke(object, objects);
        System.out.println("提交事务");
        return returnValue;
    }

    public static void main(String[] args) {
        UserDAO userDAO =  (UserDAO)new CglibProxyFactory(new UserDAOImpl()).getProxyInstance();
        userDAO.save();
    }
}
