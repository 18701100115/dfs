package cn.demo.dfs.api;

import cn.demo.dfs.hbase.HbaseDemo;
import cn.demo.dfs.hbase.User;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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



    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test2(@RequestParam("userTable") String userTable) {

        try {
            hbaseDemo.createTable(userTable, new String[] { "information", "contact" });
            User user = new User("001", "xiaoming", "123456", "man", "20", "13355550021", "1232821@csdn.com");
            hbaseDemo.insertData(userTable, user);
            User user2 = new User("002", "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com");
            hbaseDemo.insertData(userTable, user2);
            List<User> list = hbaseDemo.getAllData(userTable);
            logger.info("--------------------插入两条数据后--------------------");
            for (User user3 : list){
                logger.info(user3.toString());
            }
            logger.info("--------------------获取原始数据-----------------------");
            hbaseDemo.getNoDealData(userTable);
            logger.info("--------------------根据rowKey查询--------------------");
            User user4 = hbaseDemo.getDataByRowKey(userTable, "user-001");
            logger.info(user4.toString());
            logger.info("--------------------获取指定单条数据-------------------");
            String user_phone = hbaseDemo.getCellData(userTable, "user-001", "contact", "phone");
            logger.info(user_phone);
            User user5 = new User("test-003", "xiaoguang", "789012", "man", "22", "12312132214", "856832@csdn.com");
            hbaseDemo.insertData(userTable, user5);
            List<User> list2 = hbaseDemo.getAllData(userTable);
            logger.info("--------------------插入测试数据后--------------------");
            for (User user6 : list2){
                logger.info(user6.toString());
            }
//            HbaseDemo.deleteByRowKey(userTable, "user-test-003");
//            List<User> list3 = HbaseDemo.getAllData(userTable);
//            logger.info("--------------------删除测试数据后--------------------");
//            for (User user7 : list3){
//                logger.info(user7.toString());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "/createTable",method = RequestMethod.GET)
    public String createTable(@RequestParam("tableName") String tableName) {
        try {
            hbaseDemo.createTable(tableName, new String[] { "information", "contact" });
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
    @RequestMapping(value = "/getNoDealData",method = RequestMethod.GET)
    public String getNoDealData(@RequestParam("tableName") String tableName) {
        ResultScanner resultScanner =  hbaseDemo.getNoDealData(tableName);
                    for(Result result: resultScanner){
                System.out.println("scan:  " + result);
                 }
        return "ok";
    }
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public String findById(@RequestParam("tableName") String tableName,@RequestParam("id") String id) {
        User user  = null;
        try {
              user = hbaseDemo.getDataByRowKey(tableName, id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }
    @RequestMapping(value = "/findByRow",method = RequestMethod.GET)
    public String findByRow(@RequestParam("tableName") String tableName,@RequestParam("id") String id) {
        String user_phone = hbaseDemo.getCellData(tableName, id, "contact", "phone");
        return user_phone;
    }
    @RequestMapping(value = "/findByAll",method = RequestMethod.GET)
    public String findByAll(@RequestParam("tableName") String tableName) {
        List<User> list2 = hbaseDemo.getAllData(tableName);
        return "ok";
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
    }


