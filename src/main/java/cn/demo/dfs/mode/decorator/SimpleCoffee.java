package cn.demo.dfs.mode.decorator;
/**
 * 原味咖啡
 */
class SimpleCoffee implements Coffee {

    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public String getIngredients() {
        return "Coffee";
    }
}
