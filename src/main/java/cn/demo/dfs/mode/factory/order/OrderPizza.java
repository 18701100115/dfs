package cn.demo.dfs.mode.factory.order;

import cn.demo.dfs.mode.factory.pizza.CheesePizza;
import cn.demo.dfs.mode.factory.pizza.GreekPizza;
import cn.demo.dfs.mode.factory.pizza.PepperPizza;
import cn.demo.dfs.mode.factory.pizza.Pizza;

public class OrderPizza {
    SimpleFactory simpleFactory;
    Pizza pizza = null;

    public OrderPizza(SimpleFactory simpleFactory, String orderType) {
        setSimpleFactory(simpleFactory,orderType);
    }

    public void setSimpleFactory(SimpleFactory simpleFactory, String orderType) {
        this.simpleFactory = simpleFactory;
        pizza = this.simpleFactory.createPizza(orderType);
        if(null!=pizza){
            pizza.bake();
            pizza.prepare();
            pizza.box();
            pizza.cut();
        }else{
            System.out.println("订购披萨失败");
        }

    }
//
//    public OrderPizza(String orderType) {
//        Pizza pizza = null;
////        do{
//            if("greek".equals(orderType)){
//                pizza = new GreekPizza();
//            }
//            else if("cheese".equals(orderType)){
//                pizza = new CheesePizza();
//            }
//            else if("pepper".equals(orderType)){
//                pizza = new PepperPizza();
//            }
//            else{
//                System.out.println("为找到商品");
//            }
//            pizza.setName(orderType);
////        }while(true);
//        pizza.bake();
//        pizza.prepare();
//        pizza.box();
//        pizza.cut();
//    }

    public static void main(String[] args) {
        //      通过类型实例化对象
        new OrderPizza(new SimpleFactory(),"greek");
    }
}
