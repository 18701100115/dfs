package cn.demo.dfs.mode.factory.pizza;

public abstract class Pizza {
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
