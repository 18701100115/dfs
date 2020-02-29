package cn.demo.dfs.intelnet;

import cn.demo.dfs.hbase.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//@Component
public class UDPFileClient01 {
     Logger logger = LoggerFactory.getLogger(getClass());
    public static void main(String[] args)throws Exception {
        byte[] data = new byte[1024*60];
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        DatagramSocket socket = new DatagramSocket(9999);

        while (true){
           System.out.println("接受数据中....");
            socket.receive(datagramPacket);
            File f= new File("G:"+File.separator+"timg.gif");
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(datagramPacket.getData(),0,datagramPacket.getLength());
            fileOutputStream.close();
             System.out.println("接受到数据:");
        }

    }
}
