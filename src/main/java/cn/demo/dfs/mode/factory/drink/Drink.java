package cn.demo.dfs.mode.factory.drink;

public abstract class Drink {
    private String name;
    public abstract void prepare();

    public void bake(){
        System.out.println(name + " baking~~~");
    }
    public void cut(){
        System.out.println(name + " cuting~~~");
    }
    public void box(){
        System.out.println(name + " boxing~~~");
    }
    public void setName(String name) {
        this.name = name;
    }
}
