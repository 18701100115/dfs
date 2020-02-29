package cn.demo.dfs.intelnet;

import java.net.InetSocketAddress;
import java.net.URL;

public class UrlTest {
    public static void main(String[] args) throws Exception{
        URL url =new URL("http://www.baidu.com:8081/index.html?name=majunjie&age=18#b");//会抛出格式化协议异常
        System.out.println("协议："+url.getProtocol());
        System.out.println("域名IP："+url.getHost());
        System.out.println("端口："+url.getPort());
        System.out.println("默认端口："+url.getDefaultPort());
        System.out.println("请求资源带参数："+url.getFile());
        System.out.println("请求资源不带参数："+url.getPath());
        System.out.println("参数："+url.getQuery());
        System.out.println("锚点："+url.getRef());

    }
}
