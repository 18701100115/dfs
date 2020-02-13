package cn.demo.dfs.mode.adapter;

/**
 * 类适配器
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void method2() {
        System.out.println("method2");
    }

    public static void main(String[] args) {
        ClassAdapter adapter = new ClassAdapter();
        adapter.method1();
        adapter.method2();
    }
}
