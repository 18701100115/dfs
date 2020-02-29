package cn.demo.dfs.intelnet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ThreadSend implements Runnable{
    private String sendHost;
    private int sendPort;
    private int localPort;
    private BufferedReader bufferedReader;
    DatagramSocket datagramSocket ;
    ;

    public ThreadSend(String sendHost, int sendPort, int localPort) {
        this.sendHost = sendHost;
        this.sendPort = sendPort;
        this.localPort = localPort;
        try {
            datagramSocket  = new DatagramSocket(localPort);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while(true){
            try {
                 String  str =   bufferedReader.readLine();
                byte[] datas = str.getBytes();
                InetSocketAddress address  = new InetSocketAddress(sendHost,sendPort);
                DatagramPacket datagramPacket = new DatagramPacket(datas,0,datas.length,address);
                datagramSocket.send(datagramPacket);
                if("bye".equals(str)){
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        datagramSocket.close();
    }
}
