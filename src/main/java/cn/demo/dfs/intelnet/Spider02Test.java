package cn.demo.dfs.intelnet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 模拟浏览器
 */
public class Spider02Test {
    public static void main(String[] args) throws Exception{
        URL url =new URL("http://www.dianping.com");//会抛出格式化协议异常
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        URLConnection.setDefaultRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");//模拟浏览器
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String msg = null;
        while (null!=(msg=bufferedInputStream.readLine())){
            System.out.println(msg);
        }
    }
}
