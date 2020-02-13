package cn.demo.dfs.mode.factory.order;

import cn.demo.dfs.mode.factory.pizza.CheesePizza;
import cn.demo.dfs.mode.factory.pizza.GreekPizza;
import cn.demo.dfs.mode.factory.pizza.PepperPizza;
import cn.demo.dfs.mode.factory.pizza.Pizza;

/**
 * 简单工厂模式
 */
public class SimpleFactory {
    public Pizza createPizza(String orderType){
        Pizza pizza = null;
        if("cheese".equals(orderType)){
            pizza = new CheesePizza();
        }
        else if("greek".equals(orderType)){
            pizza = new GreekPizza();
        }
        else if("pepper".equals(orderType)){
            pizza = new PepperPizza();
        }
        pizza.setName(orderType);
        return pizza;
    }
}
