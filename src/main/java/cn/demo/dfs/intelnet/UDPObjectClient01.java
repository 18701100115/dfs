package cn.demo.dfs.intelnet;

import cn.demo.dfs.hbase.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//@Component
public class UDPObjectClient01 {
     Logger logger = LoggerFactory.getLogger(getClass());
    public static void main(String[] args)throws Exception {
        byte[] data = new byte[1024*60];
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        DatagramSocket socket = new DatagramSocket(9999);
        while (true){
            System.out.println("接受数据中....");
            socket.receive(datagramPacket);
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(datagramPacket.getData()));
            System.out.println("接受到数据:"+objectInputStream.readUTF());
            System.out.println("接受到数据:"+objectInputStream.readBoolean());
            System.out.println("接受到数据:"+objectInputStream.readInt());
            System.out.println("接受到数据:"+objectInputStream.readChar());
            User user = (User)objectInputStream.readObject();
            System.out.println("接受到数据:"+JSON.toJSONString(user));
        }

    }
}
