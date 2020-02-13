package cn.demo.dfs.mode.adapter;

/**
 * 接口适配器
 */
public abstract  class IAdapter implements Target{
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    public static void main(String[] args) {
        IAdapter adapter =    new IAdapter(){
            @Override
            public void method1() {
                System.out.println("method1");

            }

        };
        adapter.method1();
        adapter.method2();
    }
}
