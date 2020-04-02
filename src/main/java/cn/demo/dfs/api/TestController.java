package cn.demo.dfs.api;

import cn.demo.dfs.mongo.Account;
import cn.demo.dfs.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/test")
public class TestController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoTemplate mongoTemplate;
    @RequestMapping(value = "/getJson",method = RequestMethod.GET)
    public String getJson(String value) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.ENGLISH);
        List<Account> list =  new ArrayList<Account>();
        for(int i=0;i<10;i++){
//            sdf.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
            list.add(new Account(RandomStringUtils.randomAlphanumeric(12),"用户"+RandomStringUtils.randomAlphanumeric(12),"123456",sdf.parse(value)));
        }
        long startTime = System.currentTimeMillis();
        mongoTemplate.insertAll(list);
        System.out.println((System.currentTimeMillis()-startTime)/1000);
        return "";
    }

    public static void main(String[] args) throws  Exception{
//        URL url = new URL("http://192.168.1.57:8190/a.txt");
//        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//        httpURLConnection.setDoOutput(true);
//        httpURLConnection.setDoInput(true);
//
//        httpURLConnection.setUseCaches(false);
//
//        httpURLConnection.setRequestProperty("Content-type", "application/json");
//
//        httpURLConnection.setRequestMethod("POST");
//
//        httpURLConnection.connect();
//        HttpUtils httpUtils = HttpUtils.getInstance();
//        String str = httpUtils.get("http://192.168.1.57:8190/a.txt");
//        System.out.println(str);
        JSONArray array = new JSONArray();
        for(int i=0;i<100000;i++){
        JSONObject obj = new JSONObject();
        obj.put("_id",RandomStringUtils.randomAlphanumeric(24));
        obj.put("liveId","3046805");
        obj.put("roomId","12014359");
        obj.put("publisherId",RandomStringUtils.randomAlphanumeric(24));
        obj.put("ip","161.117.143.176");
        obj.put("data_type","2003");
        obj.put("data_key","cpu usage");
        obj.put("data_value","12.0%,12.0%,15.0%,-2147483648.0%");
        obj.put("report_time","2020-03-18 00:00:03");
        obj.put("viewer_id","bf00a41a7b994874b34b98d5dbd6748b");
        obj.put("role","1");
        obj.put("video_permission","1");
        obj.put("doc_permission","1");
        obj.put("client_time","2020-03-17 23:59:32");
            array.add(obj);
        }
        File f = new File("D:"+File.separator+"a.txt");
        PrintStream printStream = new PrintStream(f);
        printStream.println(array.toJSONString());


    }

    }


