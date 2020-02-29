package cn.demo.dfs.intelnet;

public class StudentMessage {
    public static void main(String[] args) {
        new Thread(new ThreadReceive(3333)).start();
        new Thread(new ThreadSend("127.0.0.1",2222,9999)).start();

    }
}
