package cn.demo.dfs.intelnet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPTypeServer01 {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket(8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        dataOutputStream.writeUTF("臭臭的小猪猪");
        dataOutputStream.writeBoolean(true);
        dataOutputStream.writeInt(18);
        dataOutputStream.writeChar('a');
        dataOutputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",9999);
        DatagramPacket datagramPacket = new DatagramPacket(bytes,0,bytes.length,address);
        socket.send(datagramPacket);
        System.out.println("发送数据包,"+bytes);
        socket.close();
        System.out.println("发送数据包完毕1");

    }
}
