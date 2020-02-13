package cn.demo.dfs.mode.factory.pizza;

public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("制作奶酪披萨准备材料");
    }
}
