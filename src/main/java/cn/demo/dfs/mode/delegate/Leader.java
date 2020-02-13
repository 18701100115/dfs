package cn.demo.dfs.mode.delegate;

import java.util.HashMap;
import java.util.Map;

/***
 * 委派模式
 */
public class Leader implements ITarget{
    private Map<String,ITarget> targetMap = new HashMap<String,ITarget>();

    public Leader() {
        targetMap.put("A",new TargetA());
        targetMap.put("B",new TargetB());
    }

    @Override
    public void work(String command) {
        targetMap.get(command).work(command);
    }

    public static void main(String[] args) {
        new Leader().work("A");
        new Leader().work("B");
    }
}
