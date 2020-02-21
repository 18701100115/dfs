package cn.demo.dfs.api;

import cn.demo.dfs.hbase.User;
import cn.demo.dfs.utils.EsUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
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
    KafkaTemplate kafkaTemplate;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    EsUtils esUtils;

    @RequestMapping(value = "/esInsert",method = RequestMethod.GET)
    public String esInsert(String value) {
        logger.info("es日志收集esInsert");
        User user = new User(UUID.randomUUID().toString().replace("-",""), "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com");
        esUtils.save(user);
        return "ok";
    }
    @RequestMapping(value = "/esInsertList",method = RequestMethod.GET)
    public String esInsertList() {
        logger.info("es日志收集esInsertList");
        List<User> list = new ArrayList<User>();
        IntStream.range(0, 999).forEach(i ->
                list.add(new User(UUID.randomUUID().toString().replace("-",""), "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com")));
        esUtils.addBatch(list);
        return "ok";
    }
    @RequestMapping(value = "/esFindByAll",method = RequestMethod.GET)
    public String esFindByAll() {
//        kafkaTemplate.send("kafkalog","你好");
        logger.info("es日志收集esFindByAll");
        logger.warn("es日志收集esFindByAll");
        logger.error("es日志收集esFindByAll");
        try {
            logger.trace("es日志收集esFindByAll");
        } catch (Exception e) {
        }
        logger.debug("es日志收集esFindByAll");
        List<User> userList = esUtils.findByAll(User.class);
        return JSON.toJSONString(userList);
    }

    }


