package cn.demo.dfs.intelnet;

import org.apache.commons.lang3.StringUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(8080);
        Socket socket = server.accept();
        System.out.println("一个客户端建立了连接");
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes,0,bytes.length);
//        System.out.println(new String(bytes,0,bytes.length));
        String str = inputStream.readUTF();
        System.out.println(str);
        String[] strs = StringUtils.split(str,"&");
        String name = StringUtils.split(strs[0],"=")[1];
        String pass = StringUtils.split(strs[1],"=")[1];
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        if("admin".equals(name)&&"admin123".equals(pass)){
            System.out.println("登录成功");
            out.writeUTF("登录成功");

        }else{
            System.out.println("登录成功");
            out.writeUTF("登录失败");
        }
        out.close();


    }
}
