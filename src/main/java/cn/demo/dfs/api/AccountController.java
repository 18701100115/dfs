package cn.demo.dfs.api;

import cn.demo.dfs.hbase.HbaseDemo;
import cn.demo.dfs.hbase.User;
import cn.demo.dfs.utils.DateFormatUtils;
import cn.demo.dfs.utils.EsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/account")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    HbaseDemo hbaseDemo;
    @Autowired
    EsUtils esUtils;

    /**
     * 开户
     */
    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test( String value) {
        stringRedisTemplate.opsForValue().set("a",value);
        logger.info("test==================");
            return "ss";
    }
    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public String test1(@RequestParam("value") String value) {
        logger.info("test1==================");
        return stringRedisTemplate.opsForValue().get("a");
    }


    @RequestMapping(value = "/createTable",method = RequestMethod.GET)
    public String createTable(@RequestParam("tableName") String tableName) {
        try {
            hbaseDemo.createTable(tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public String insert(@RequestParam("tableName") String tableName) {
        try {
            User user2 = new User(UUID.randomUUID().toString().replace("-",""), "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com");
            hbaseDemo.insertData(tableName, user2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    @RequestMapping(value = "/insertList",method = RequestMethod.GET)
    public String insertList(@RequestParam("tableName") String tableName) {
        List<User> list = new ArrayList<User>();
        try {
            for(int i=0;i<100000;i++){
                User user = new User(UUID.randomUUID().toString().replace("-",""), "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com");
                list.add(user);
            }
            logger.info("开始插入数据,===="+ DateFormatUtils.getDateStr(new Date()));
            hbaseDemo.insertDataList(tableName, list);
            logger.info("插入数据完毕,===="+ DateFormatUtils.getDateStr(new Date()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    @RequestMapping(value = "/getNoDealData",method = RequestMethod.GET)
    public JSONObject getNoDealData(@RequestParam("tableName") String tableName) {
        ResultScanner resultScanner =  hbaseDemo.getNoDealData(tableName);
                    for(Result result: resultScanner){
                System.out.println("scan:  " + result);
                 }
        return JSONObject.parseObject(JSON.toJSONString(resultScanner));
    }
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public String findById(@RequestParam("tableName") String tableName,@RequestParam("id") String id) {
        User user  = null;
        try {
              user = hbaseDemo.getDataByRowKey(tableName, id,User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(user);
    }
    @RequestMapping(value = "/findByRow",method = RequestMethod.GET)
    public String findByRow(@RequestParam("tableName") String tableName,@RequestParam("id") String id) {
        String user_phone = hbaseDemo.getCellData(tableName, id, "contact", "phone");
        return user_phone;
    }
    @RequestMapping(value = "/findByAll",method = RequestMethod.GET)
    public String findByAll(@RequestParam("tableName") String tableName) {
        List<User> list = hbaseDemo.getAllData(tableName,User.class);
        return JSONArray.toJSONString(list);
    }
    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    public String deleteById(@RequestParam("tableName") String tableName,@RequestParam("id") String id) {
        try {
            hbaseDemo.deleteByRowKey(tableName, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    @RequestMapping(value = "/deleteByTable",method = RequestMethod.GET)
    public String deleteByTable(@RequestParam("tableName") String tableName) {
            hbaseDemo.deleteTable(tableName);
        return "ok";
    }

    @RequestMapping(value = "/esInsert",method = RequestMethod.GET)
    public String esInsert(String value) {
        User user = new User(UUID.randomUUID().toString().replace("-",""), "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com");
        esUtils.save(user);
        return "ok";
    }
    @RequestMapping(value = "/esInsertList",method = RequestMethod.GET)
    public String esInsertList() {
        List<User> list = new ArrayList<User>();
        IntStream.range(0, 999).forEach(i ->
                list.add(new User(UUID.randomUUID().toString().replace("-",""), "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com")));
        esUtils.addBatch(list);
        return "ok";
    }
    @RequestMapping(value = "/esFindByAll",method = RequestMethod.GET)
    public String esFindByAll() {
       List<User> userList = esUtils.findByAll(User.class);
        return JSON.toJSONString(userList);
    }

    }


