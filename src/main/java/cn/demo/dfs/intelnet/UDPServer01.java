package cn.demo.dfs.intelnet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPServer01 {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket(8888);
        String data = "烦人的小猪猪";
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",9999);
        DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(),0,data.getBytes().length,address);
        socket.send(datagramPacket);
        System.out.println("发送数据包,"+data);
        socket.close();
        System.out.println("发送数据包完毕1");

    }
}
