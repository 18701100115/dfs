package cn.demo.dfs.zk;

import org.apache.zookeeper.*;

public class ZookeeperDemo {
    static   ZooKeeper zooKeeper = null;
    public void get(){

    }
    public static void main(String[] args)throws Exception{
      zooKeeper = new ZooKeeper("192.168.26.132:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType());
                System.out.println(watchedEvent.getPath());
                System.out.println(watchedEvent.getState());
                System.out.println(watchedEvent.getWrapper());
                try {
                    byte[] rst = zooKeeper.getData("/majunjie1",true,null);
                    System.out.println(new String(rst));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        zooKeeper.create("/majunjie1/aac1","你好".getBytes("UTF-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        byte[] rst = zooKeeper.getData("/majunjie1",true,null);
//        System.out.println(new String(rst));
//        while (true){
//
//        }
        zooKeeper.close();
    }
}
