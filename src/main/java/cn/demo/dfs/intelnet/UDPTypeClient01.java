package cn.demo.dfs.intelnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//@Component
public class UDPTypeClient01 {
     Logger logger = LoggerFactory.getLogger(getClass());
    public static void main(String[] args)throws Exception {
        byte[] data = new byte[1024*60];
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        DatagramSocket socket = new DatagramSocket(9999);
        while (true){
            System.out.println("接受数据中....");
            socket.receive(datagramPacket);
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(datagramPacket.getData()));
            System.out.println("接受到数据:"+dataInputStream.readUTF());
            System.out.println("接受到数据:"+dataInputStream.readBoolean());
            System.out.println("接受到数据:"+dataInputStream.readInt());
            System.out.println("接受到数据:"+dataInputStream.readChar());
        }

    }
}
