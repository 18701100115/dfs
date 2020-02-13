package cn.demo.dfs.mode.factory.pizza;

public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("制作希腊披萨准备材料");
    }
}
