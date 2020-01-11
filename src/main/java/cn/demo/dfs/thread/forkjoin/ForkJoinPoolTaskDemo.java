package cn.demo.dfs.thread.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTaskDemo {
    public static void main(String[] args) throws Exception{
       int[] array = new int[100];
        Random random = new Random();
        int total = 0;
        for(int i=0;i<array.length;i++){
            int temp = random.nextInt(20);
            total +=(array[i]=temp);
        }
        System.out.println("总和："+total);
        SumTask sumTask = new SumTask(array,0,array.length);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Future<Integer> future = pool.submit(sumTask);
        System.out.println("多线程结果:"+future.get());
        pool.shutdown();
    }
}
