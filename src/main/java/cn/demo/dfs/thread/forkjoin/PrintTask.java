package cn.demo.dfs.thread.forkjoin;

import java.util.concurrent.RecursiveAction;

public class PrintTask extends RecursiveAction {
    private  int threshold = 50;
    private  int start;
    private  int end;

    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        if(end-start<threshold){
            for(int i=start;i<end;i++){
                System.out.println(Thread.currentThread().getName()+"的i值"+i);
            }
        }else{
            int middle = (start+end)/2;
            PrintTask left = new PrintTask(start,middle);
            PrintTask right = new PrintTask(middle,end);
            left.fork();
            right.fork();

        }

    }
}
