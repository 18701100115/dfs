package cn.demo.dfs.mode.factory.order;

import cn.demo.dfs.mode.factory.drink.Drink;
import cn.demo.dfs.mode.factory.order.impl.ChesseAbFactory;
import cn.demo.dfs.mode.factory.order.impl.GreekAbFactory;
import cn.demo.dfs.mode.factory.order.impl.PepperAbFactory;
import cn.demo.dfs.mode.factory.pizza.Pizza;

/**
 * 抽象工厂模式
 */
public interface AbFactory {
    public Drink createDrink();
    public Pizza createPizza();

    public static void main(String[] args) {
        AbFactory factory = new PepperAbFactory();
        Drink drink = factory.createDrink();
        Pizza pizza = factory.createPizza();
        drink.prepare();
        pizza.prepare();
    }
}
