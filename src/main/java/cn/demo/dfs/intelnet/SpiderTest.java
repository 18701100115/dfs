package cn.demo.dfs.intelnet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class SpiderTest {
    public static void main(String[] args) throws Exception{
        URL url =new URL("https://www.jd.com");//会抛出格式化协议异常
        InputStream inputStream = url.openStream();
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
        String msg = null;
        while (null!=(msg=bufferedInputStream.readLine())){
            System.out.println(msg);
        }
    }
}
