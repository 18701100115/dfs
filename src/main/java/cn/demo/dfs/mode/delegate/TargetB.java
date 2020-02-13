package cn.demo.dfs.mode.delegate;

public class TargetB implements  ITarget {

    @Override
    public void work(String command) {
        System.out.println("TargetB.....设计一张图片");
    }
}
