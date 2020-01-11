package cn.demo.dfs.thread.forkjoin;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ForkJoinDemo {
    public static void main(String[] args) {
        PrintTask printTask = new PrintTask(0,300);
        ForkJoinPool forkJoinPool = new ForkJoinPool(16);
        forkJoinPool.execute(printTask);
        try {
            forkJoinPool.awaitTermination(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        forkJoinPool.shutdown();
    }
}
