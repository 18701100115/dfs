package cn.demo.dfs.intelnet;

public class TeacherMessage {
    public static void main(String[] args) {
        new Thread(new ThreadReceive(2222)).start();
        new Thread(new ThreadSend("127.0.0.1",3333,8888)).start();

    }
}
