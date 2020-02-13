package cn.demo.dfs.mode.decorator;
/**
 * 咖啡
 */
interface Coffee {
    /** 获取价格 */
    double getCost();
    /** 获取配料 */
    String getIngredients();
}