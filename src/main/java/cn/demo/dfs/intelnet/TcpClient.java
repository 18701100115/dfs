package cn.demo.dfs.intelnet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1",8080);
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名：");
        String name = in.readLine();
        System.out.println("请输入密码：");
        String pass = in.readLine();
        String data = "name="+name+"&pass="+pass;
        outputStream.writeUTF(data);

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        String str = inputStream.readUTF();
        System.out.println(str);

        outputStream.flush();
        outputStream.close();
        socket.close();

    }
}
