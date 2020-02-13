package cn.demo.dfs.mode.delegate;

public class TargetA implements  ITarget {

    @Override
    public void work(String command) {
        System.out.println("TargetA。。。。开发一段代码");
    }
}
