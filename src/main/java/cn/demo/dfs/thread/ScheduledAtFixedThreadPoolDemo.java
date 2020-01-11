package cn.demo.dfs.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixedThreadPoolDemo {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        System.out.println("延迟三秒执行开始："+sdf.format(new Date()));
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"running");
                System.out.println("延迟三秒执行结束："+sdf.format(new Date()));

            }
        }, 1, 1,TimeUnit.SECONDS);
//         executorService.shutdown();
    }
}
