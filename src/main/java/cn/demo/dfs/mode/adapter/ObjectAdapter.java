package cn.demo.dfs.mode.adapter;

/**
 * 对象适配器
 */
public class ObjectAdapter implements Target{

    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void method1() {
        adaptee.method1();
    }

    @Override
    public void method2() {
        System.out.println("method2");
    }

    public static void main(String[] args) {
        ObjectAdapter adapter = new ObjectAdapter(new Adaptee());
        adapter.method1();
        adapter.method2();
    }
}
