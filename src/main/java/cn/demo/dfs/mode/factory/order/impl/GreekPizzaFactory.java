package cn.demo.dfs.mode.factory.order.impl;

import cn.demo.dfs.mode.factory.order.IFactory;
import cn.demo.dfs.mode.factory.pizza.CheesePizza;
import cn.demo.dfs.mode.factory.pizza.GreekPizza;
import cn.demo.dfs.mode.factory.pizza.Pizza;

public class GreekPizzaFactory implements IFactory {
    @Override
    public Pizza createPizzaOrder() {
        return new GreekPizza();
    }
}
