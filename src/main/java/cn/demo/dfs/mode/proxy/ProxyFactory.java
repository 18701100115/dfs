package cn.demo.dfs.mode.proxy;

import cn.demo.dfs.hbase.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 静态代理
 */
public class ProxyFactory   {

    public static Object  getProxyInstance(String classStr){
        try {
        Class   classz = Class.forName(classStr);
        Object obj = classz.newInstance();
      return   Proxy.newProxyInstance(
                classz.getClassLoader(),
                classz.getInterfaces(),
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("开始事务2");
                    //执行目标对象方法
                    Object returnValue = method.invoke(obj, args);
                    System.out.println("提交事务2");
                    return returnValue;
                }
            }
    );
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return null;
}
    public static void main(String[] args) {
        UserDAO userDAO = (UserDAO) ProxyFactory.getProxyInstance("cn.demo.dfs.mode.proxy.UserDAOImpl");
        userDAO.save();
    }
}
