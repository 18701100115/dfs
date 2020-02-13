package cn.demo.dfs.mode.singletion;

/***
 * 双重检查单例模式 volatile
 *  懒加载，不浪费资源，存在线程安全问题
 */
public class SingletonTest06 {

    private   static volatile SingletonTest06 singletonTest06;
    private SingletonTest06() {
    }

    public  static SingletonTest06 getInstance(){

        if(null== singletonTest06){
            synchronized (SingletonTest06.class){
                if(null== singletonTest06) {
                    singletonTest06 = new SingletonTest06();
                }
            }
        }
        return singletonTest06;
    }
    public static void main(String[] args) {
        SingletonTest06 s1 = SingletonTest06.getInstance();
        SingletonTest06 s2 = SingletonTest06.getInstance();
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1==s2);

    }
}
