//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.demo.dfs.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class HttpUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static HttpUtils clientUtils = null;
    private String charSet = "UTF-8";

    public HttpUtils() {
    }

    private HttpClient getHttpClient() {
        HttpParams mHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(mHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(mHttpParams, 20000);
        HttpConnectionParams.setSocketBufferSize(mHttpParams, 8192);
        HttpClientParams.setRedirecting(mHttpParams, true);
        HttpClient httpClient = new DefaultHttpClient(mHttpParams);
        return httpClient;
    }

    public static HttpUtils getInstance() {
        if (clientUtils == null) {
            clientUtils = new HttpUtils();
        }

        return clientUtils;
    }

    public String getCharSet() {
        return this.charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String get(String url)   {
        String result = null;
        try {
            result = "";
            HttpClient httpClient = this.getHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(request);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpResponse.getFirstHeader("Content-Encoding") != null && httpResponse.getFirstHeader("Content-Encoding").getValue().equals("gzip")) {
                    result = EntityUtils.toString(new GzipDecompressingEntity(httpEntity), this.charSet);
                } else {
                    result = EntityUtils.toString(httpEntity, this.charSet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String post(String url, JSONObject args)  {
        String result = "";
        HttpClient httpClient = this.getHttpClient();
        HttpPost request = new HttpPost(url);
        List<BasicNameValuePair> postData = new ArrayList();
        Iterator var7 = args.entrySet().iterator();

        while(var7.hasNext()) {
            Entry<String, String> entry = (Entry)var7.next();
            postData.add(new BasicNameValuePair(entry.getKey().toString(), String.valueOf(entry.getValue())));
        }

        try {
            request.setEntity(new UrlEncodedFormEntity(postData, this.charSet));
            HttpResponse httpResponse = httpClient.execute(request);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpResponse.getFirstHeader("Content-Encoding") != null && httpResponse.getFirstHeader("Content-Encoding").getValue().equals("gzip")) {
                    result = EntityUtils.toString(new GzipDecompressingEntity(httpEntity), this.charSet);
                } else {
                    result = EntityUtils.toString(httpEntity, this.charSet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isNotBlank(ip)) {
            String[] arr = ip.split(",");
            String[] var3 = arr;
            int var4 = arr.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String str = var3[var5];
                if (!"unknown".equalsIgnoreCase(str)) {
                    ip = str;
                    break;
                }
            }
        }

        return ip;
    }

    public static void main(String[] args) {
        HttpUtils client = HttpUtils.getInstance();
        //String requestUrl = SystemProperties.getInstance().getProperty("qiniuBitRateUrl");
       // String requestUrl = "http://7xpu6m.com1.z1.glb.clouddn.com/";
        String requestUrl = "http://7xkuri.com0.z1.glb.qiniucdn.com/";
                String url = requestUrl+"live/user/video/1520479669855/1520479669855.mp4?avinfo";
        JSONObject object = new JSONObject();
        object.put("userId","2222");
         String result = null;
            result = client.post("http://localhost:8081/account/openAccount",object);
        System.out.println("***" + result);
    }


}
