package cn.demo.dfs.intelnet;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class PortTest {
    public static void main(String[] args) throws Exception{
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",8082);
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress("localhost",9000);
        System.out.println(inetSocketAddress.getHostName());
        System.out.println(inetSocketAddress.getAddress());
        System.out.println(inetSocketAddress.getPort());
        System.out.println(inetSocketAddress2.getHostName());
        System.out.println(inetSocketAddress2.getAddress());
        System.out.println(inetSocketAddress2.getPort());

    }
}
