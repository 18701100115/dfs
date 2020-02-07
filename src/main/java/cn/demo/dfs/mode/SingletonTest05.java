package cn.demo.dfs.mode;

/***
 * 同步代码块懒汉式单例模式
 *  懒加载，不浪费资源，存在线程安全问题
 */
public class SingletonTest05 {

    private   static SingletonTest05 singletonTest05;
    private SingletonTest05() {
    }

    public  static SingletonTest05 getInstance(){

        if(null== singletonTest05){
            synchronized (SingletonTest05.class){
                singletonTest05 = new SingletonTest05();
            }
        }
        return singletonTest05;
    }
    public static void main(String[] args) {
        SingletonTest05 s1 = SingletonTest05.getInstance();
        SingletonTest05 s2 = SingletonTest05.getInstance();
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1==s2);

    }
}
