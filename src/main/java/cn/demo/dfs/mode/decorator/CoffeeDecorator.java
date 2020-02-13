package cn.demo.dfs.mode.decorator;

import com.alibaba.fastjson.JSON;

/**
 * 装饰器模式 和代理模式类似
 * 咖啡的"装饰器"，可以给咖啡添加各种"配料"
 */
 public class CoffeeDecorator implements Coffee {
    protected final Coffee coffee;

    /**
     * 在构造方法中，初始化咖啡对象的引用
     */
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }

    @Override
    public String getIngredients() {
        return coffee.getIngredients();
    }

    public static void main(String[] args) {

        //原味咖啡
        Coffee c = new SimpleCoffee();
        System.out.println(JSON.toJSONString(c));

        //增加牛奶的咖啡
        c = new WithMilk(c);
        System.out.println(JSON.toJSONString(c));
        //再加一点糖
        c = new WithSugar(c);
        System.out.println(JSON.toJSONString(c));
    }
}
