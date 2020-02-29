package cn.demo.dfs.intelnet;

import cn.demo.dfs.hbase.User;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPFileServer01 {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket(8888);
        File f = new File("D:"+File.separator+"QQ图片20200224163150.png");
        InputStream inputStream = new FileInputStream(f);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",9999);
        int len = 0 ;
        byte[] bytedata = new byte[inputStream.available()];
        inputStream.read(bytedata);
            DatagramPacket datagramPacket = new DatagramPacket(bytedata,0,bytedata.length,address);
            socket.send(datagramPacket);
            System.out.println("发送数据包,"+new String(bytedata,0,len));
            Thread.sleep(1000);
            System.out.println("发送数据包完毕1");
        socket.close();

    }
}
