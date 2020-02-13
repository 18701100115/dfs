package cn.demo.dfs.mode.factory.order;

import cn.demo.dfs.mode.factory.order.impl.ChessePizzaFactory;
import cn.demo.dfs.mode.factory.pizza.Pizza;

/**
 * 工厂方法模式
 */
public interface IFactory {
    public Pizza createPizzaOrder();

    public static void main(String[] args) {
        IFactory factory = new ChessePizzaFactory();
        Pizza pizza = factory.createPizzaOrder();
        pizza.prepare();
    }
}
