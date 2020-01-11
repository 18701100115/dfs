package cn.demo.dfs.zk;

import io.netty.util.Constant;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ConstantMonitor implements Watcher, InitializingBean {
    private static ZooKeeper zookeeper = null;
    private static ConstantMonitor constantMonitor = null;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat = new Stat();

    /**
     * zookeeper连接&初始化配置信息
     */
    public void afterPropertiesSet() throws Exception {
        constantMonitor = new ConstantMonitor();
        try {
            zookeeper = new ZooKeeper("192.168.26.132:2181", 2000, constantMonitor); // 我这里用单点演示
            countDownLatch.await();
            System.out.println("=====================》》 Zookeeper初始化配置信息：");
            Iterator<Map.Entry<String, Object>> iterator = cn.demo.dfs.zk.Constant.map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> item = iterator.next();
                String name = item.getKey();
                byte[] dataBs = zookeeper.getData("/" + name, true, stat);
                item.setValue(new String(dataBs, "UTF-8"));
                System.out.println(name + "=" + new String(dataBs, "UTF-8"));
            }
            System.out.println("《《===================== Zookeeper初始化配置信息完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监控&更新
     */
    public void process(WatchedEvent event) {
        String path = event.getPath();
        Event.KeeperState state = event.getState();
        Event.EventType type = event.getType();
        if (type == Event.EventType.None && state == Event.KeeperState.SyncConnected) {
            System.out.println("=====================》》 Zookeeper连接成功！");
            countDownLatch.countDown();
        } else if (type != Event.EventType.None) {
            try {
                byte[] dataBs = zookeeper.getData(path, true, stat);
                String name = path.substring(1);
                cn.demo.dfs.zk.Constant.map.put(name, new String(dataBs, "UTF-8"));
                System.out.println(String.format("=====================》》  配置信息【update】：%s=%s", name, new String(dataBs, "UTF-8")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


