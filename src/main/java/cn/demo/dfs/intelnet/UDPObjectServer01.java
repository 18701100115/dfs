package cn.demo.dfs.intelnet;

import cn.demo.dfs.hbase.User;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPObjectServer01 {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket(8888);
        String data = "烦人的小猪猪";
        byte[] bytes = data.getBytes();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectInputStream= new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        objectInputStream.writeUTF("臭臭的小猪猪");
        objectInputStream.writeBoolean(true);
        objectInputStream.writeInt(18);
        objectInputStream.writeChar('a');
        objectInputStream.writeObject(new User("1","2","3","3","3","3","3"));
        objectInputStream.flush();
        bytes = byteArrayOutputStream.toByteArray();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",9999);
        DatagramPacket datagramPacket = new DatagramPacket(bytes,0,bytes.length,address);
        socket.send(datagramPacket);
        System.out.println("发送数据包,"+data);
        socket.close();
        System.out.println("发送数据包完毕1");

    }
}
