package cn.demo.dfs.intelnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Component
public class UDPClient01 implements Runnable{
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run() {
        try {
            jssj();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@PostConstruct
    public void jssj()throws Exception {
        byte[] data = new byte[1024*60];
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        DatagramSocket socket = new DatagramSocket(9999);
        while (true){
            logger.info("接受数据中....");
            socket.receive(datagramPacket);
            logger.info("接受到数据:"+new String(datagramPacket.getData(),0,datagramPacket.getLength()));
        }
    }
    public static void main(String[] args)throws Exception {
        byte[] data = new byte[1024*60];
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        DatagramSocket socket = new DatagramSocket(9999);
        while (true){
            System.out.println("接受数据中....");
            socket.receive(datagramPacket);
            System.out.println("接受到数据:"+new String(datagramPacket.getData(),0,datagramPacket.getLength()));
        }

    }
}
