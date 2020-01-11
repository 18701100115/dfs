package cn.demo.dfs.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(20,100,60,TimeUnit.SECONDS,new ArrayBlockingQueue(5));
        StringBuffer ss = new StringBuffer("ss");
        poolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(ss);
                ss.append("11111");
            }
        },ss);
        poolExecutor.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ss);

    }
}
