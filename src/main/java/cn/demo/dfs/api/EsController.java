package cn.demo.dfs.api;

import cn.demo.dfs.hbase.User;
import cn.demo.dfs.mode.SingletonTest08;
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
@RequestMapping("/es")
public class EsController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
@Autowired
    SingletonTest08 singletonTest08;
@Autowired
    EsUtils esUtils;

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
       List<User> userList = singletonTest08.findByAll(User.class);
        return JSON.toJSONString(userList);
    }

    }


