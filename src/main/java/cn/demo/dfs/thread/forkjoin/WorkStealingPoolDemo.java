package cn.demo.dfs.thread.forkjoin;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkStealingPoolDemo {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newWorkStealingPool(8);
        for(int i=0;i<=100;i++){
            final int count = i;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Date now = new Date();
                System.out.println("线程："+Thread.currentThread()+ ",完成任务："+count+ "时间为：" + now.getSeconds());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

         }
        while (true){

        }
    }
}
