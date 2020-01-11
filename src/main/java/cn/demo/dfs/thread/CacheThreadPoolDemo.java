package cn.demo.dfs.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<20;i++){
            executorService.execute(new Task());
        }
        executorService.shutdown();
    }
}
