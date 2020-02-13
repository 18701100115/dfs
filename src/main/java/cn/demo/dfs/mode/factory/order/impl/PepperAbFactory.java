package cn.demo.dfs.mode.factory.order.impl;

import cn.demo.dfs.mode.factory.drink.Drink;
import cn.demo.dfs.mode.factory.drink.GreekDrink;
import cn.demo.dfs.mode.factory.drink.PepperDrink;
import cn.demo.dfs.mode.factory.order.AbFactory;
import cn.demo.dfs.mode.factory.pizza.GreekPizza;
import cn.demo.dfs.mode.factory.pizza.PepperPizza;
import cn.demo.dfs.mode.factory.pizza.Pizza;

public class PepperAbFactory implements AbFactory {

    @Override
    public Drink createDrink() {
        return new PepperDrink();
    }

    @Override
    public Pizza createPizza() {
        return new PepperPizza();
    }
}
