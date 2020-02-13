package cn.demo.dfs.mode.singletion;

/***
 * 静态代码块饿汉式
 * 可能造成内存浪费，如果使用classload进行装在可能出现多线程问题
 */
public class SingletonTest02 {

    private   static   SingletonTest02 singletonTest02;
    private SingletonTest02() {
    }
    static{
        singletonTest02 = new SingletonTest02();
    }
    public static SingletonTest02 getInstance(){
        return singletonTest02;
    }
    public static void main(String[] args) {
        SingletonTest02 s1 = SingletonTest02.getInstance();
        SingletonTest02 s2 = SingletonTest02.getInstance();
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1==s2);

    }
}
