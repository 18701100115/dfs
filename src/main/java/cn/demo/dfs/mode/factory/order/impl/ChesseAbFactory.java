package cn.demo.dfs.mode.factory.order.impl;

import cn.demo.dfs.mode.factory.drink.CheeseDrink;
import cn.demo.dfs.mode.factory.drink.Drink;
import cn.demo.dfs.mode.factory.order.AbFactory;
import cn.demo.dfs.mode.factory.order.IFactory;
import cn.demo.dfs.mode.factory.pizza.CheesePizza;
import cn.demo.dfs.mode.factory.pizza.Pizza;

public class ChesseAbFactory implements AbFactory {

    @Override
    public Drink createDrink() {
        return new CheeseDrink();
    }

    @Override
    public Pizza createPizza() {
        return new CheesePizza();
    }
}
