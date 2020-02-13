package cn.demo.dfs.mode.singletion;

/***
 * 静态内部类单例模式
 *  懒加载，内部类不会在类装载的时候实例化，使用类装载保证线程安全，不浪费资源，存在线程安全问题
 */
public class SingletonTest07 {

    private   static volatile SingletonTest07 singletonTest07;
    private SingletonTest07() {
    }

    private   static class SingletonTest07Instance{
       private static final SingletonTest07 INSTANCE = new SingletonTest07();
    }
    public static SingletonTest07 getInstance(){
        return SingletonTest07Instance.INSTANCE;
    }
    public static void main(String[] args) {
        SingletonTest07 s1 = SingletonTest07.getInstance();
        SingletonTest07 s2 = SingletonTest07.getInstance();
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1==s2);

    }
}
