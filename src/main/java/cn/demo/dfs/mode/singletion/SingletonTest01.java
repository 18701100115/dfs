package cn.demo.dfs.mode.singletion;

/***
 * 静态常量饿汉式
 * 可能造成内存浪费，如果使用classload进行装在可能出现多线程问题
 */
public class SingletonTest01 {   private   static final SingletonTest01 singletonTest01 = new SingletonTest01();
    private SingletonTest01() {
    }
    public static SingletonTest01 getInstance(){
        return singletonTest01;
    }
     public int getInt(){
        return 0;
    }
    public static void main(String[] args) {
        SingletonTest01 s1 = SingletonTest01.getInstance();
        SingletonTest01 s2 = SingletonTest01.getInstance();
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1==s2);

    }
}
