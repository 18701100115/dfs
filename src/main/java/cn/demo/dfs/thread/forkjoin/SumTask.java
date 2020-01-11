package cn.demo.dfs.thread.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {
    private  int threshold = 20;
    private  int start = 50;
    private  int end = 50;
    private  int array[];

    public SumTask(int[] array,int start, int end) {
        super();
        this.array = array;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        if(end-start<threshold){
            for(int i=start;i<end;i++){
                sum += array[i];
                System.out.println(Thread.currentThread().getName()+"的i值"+i);
            }
            return sum;
        }else{
            int middle = (start+end)/2;
            SumTask left = new SumTask(array,start,middle);
            SumTask right = new SumTask(array,middle,end);
            left.fork();
            right.fork();
            return left.join()+right.join();
        }

    }
}
