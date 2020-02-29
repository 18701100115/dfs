package cn.demo.dfs.intelnet;

import java.net.InetAddress;

public class IpTest {
    public static void main(String[] args) throws Exception{
        InetAddress address = InetAddress.getLocalHost();//获取本机
        System.out.println(address.getHostAddress());//返回地址
        System.out.println(address.getHostName());//返回计算机名
        address = InetAddress.getByName("www.163.com");//根据域名解析IP地址
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());

    }
}
