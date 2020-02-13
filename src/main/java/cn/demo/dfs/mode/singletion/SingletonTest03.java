package cn.demo.dfs.mode.singletion;

/***
 * 线程不安全懒汉式单例模式
 *  懒加载，不浪费资源，只能在单线程下使用，多线程下会出现问题
 */
public class SingletonTest03 {

    private   static SingletonTest03 singletonTest03;
    private SingletonTest03() {
    }

    public static SingletonTest03 getInstance(){

        if(null== singletonTest03){
            singletonTest03 = new SingletonTest03();
        }
        return singletonTest03;
    }
    public static void main(String[] args) {
        SingletonTest03 s1 = SingletonTest03.getInstance();
        SingletonTest03 s2 = SingletonTest03.getInstance();
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1==s2);

    }
}
