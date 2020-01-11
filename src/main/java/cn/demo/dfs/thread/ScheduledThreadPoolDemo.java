package cn.demo.dfs.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ScheduledThreadPoolDemo {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        System.out.println("延迟三秒执行开始："+sdf.format(new Date()));
         executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟三秒执行结束："+sdf.format(new Date()));

            }
        }, 3, TimeUnit.SECONDS);
         executorService.shutdown();
    }
}
