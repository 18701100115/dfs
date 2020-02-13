package cn.demo.dfs.mode.singletion;

/***
 * 线程安全懒汉式单例模式
 *  懒加载，不浪费资源，可以解决线程安全问题，频繁调用情况下存在效率问题
 */
public class SingletonTest04 {

    private   static SingletonTest04 singletonTest04;
    private SingletonTest04() {
    }

    public synchronized static SingletonTest04 getInstance(){

        if(null== singletonTest04){
            singletonTest04 = new SingletonTest04();
        }
        return singletonTest04;
    }
    public static void main(String[] args) {
        SingletonTest04 s1 = SingletonTest04.getInstance();
        SingletonTest04 s2 = SingletonTest04.getInstance();
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1==s2);

    }
}
