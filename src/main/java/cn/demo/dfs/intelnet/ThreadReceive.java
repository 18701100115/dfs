package cn.demo.dfs.intelnet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ThreadReceive implements Runnable {
    private int port;

    public ThreadReceive(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            recevie();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void recevie()throws Exception{
        DatagramSocket datagramSocket =new DatagramSocket(port);
        byte[] data = new byte[1024*60];
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        while(true){
            datagramSocket.receive(datagramPacket);
            String message = new String(data,0,data.length);
            System.out.println(message);
            if("bye".equals(message)){
                break;
            }
        }

    }
}
