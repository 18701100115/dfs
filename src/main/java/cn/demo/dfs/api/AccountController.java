package cn.demo.dfs.api;

import cn.demo.dfs.hbase.HbaseDemo;
import cn.demo.dfs.hbase.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
    public String test2(@RequestParam("value") String value) {
        try {
            HbaseDemo.createTable("user_table", new String[] { "information", "contact" });
            User user = new User("001", "xiaoming", "123456", "man", "20", "13355550021", "1232821@csdn.com");
            HbaseDemo.insertData("user_table", user);
            User user2 = new User("002", "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com");
            HbaseDemo.insertData("user_table", user2);
            List<User> list = HbaseDemo.getAllData("user_table");
            System.out.println("--------------------插入两条数据后--------------------");
            for (User user3 : list){
                System.out.println(user3.toString());
            }
            System.out.println("--------------------获取原始数据-----------------------");
            HbaseDemo.getNoDealData("user_table");
            System.out.println("--------------------根据rowKey查询--------------------");
            User user4 = HbaseDemo.getDataByRowKey("user_table", "user-001");
            System.out.println(user4.toString());
            System.out.println("--------------------获取指定单条数据-------------------");
            String user_phone = HbaseDemo.getCellData("user_table", "user-001", "contact", "phone");
            System.out.println(user_phone);
            User user5 = new User("test-003", "xiaoguang", "789012", "man", "22", "12312132214", "856832@csdn.com");
            HbaseDemo.insertData("user_table", user5);
            List<User> list2 = HbaseDemo.getAllData("user_table");
            System.out.println("--------------------插入测试数据后--------------------");
            for (User user6 : list2){
                System.out.println(user6.toString());
            }
            HbaseDemo.deleteByRowKey("user_table", "user-test-003");
            List<User> list3 = HbaseDemo.getAllData("user_table");
            System.out.println("--------------------删除测试数据后--------------------");
            for (User user7 : list3){
                System.out.println(user7.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
    }


//    nohup ./etcd --name docker-node1 \
//            --initial-advertise-peer-urls http://192.168.37.130:2380 \
//            --listen-peer-urls http://192.168.37.130:2380 \
//            --listen-client-urls http://192.168.37.130:2379,http://127.0.0.1:2379 \
//            --advertise-client-urls chttp://192.168.37.130:2379 \
//            --initial-cluster-token etcd-cluster \
//            --initial-cluster docker-node1=http://192.168.37.130:2380,docker-node2=http://192.168.37.131:2380 \
//            --initial-cluster-state new&
//
//
//
//            nohup ./etcd --name docker-node1 \
//            --initial-advertise-peer-urls http://192.168.37.131:2380 \
//            --listen-peer-urls http://192.168.37.131:2380 \
//            --listen-client-urls http://192.168.37.131:2379,http://127.0.0.1:2379 \
//            --advertise-client-urls chttp://192.168.37.131:2379 \
//            --initial-cluster-token etcd-cluster \
//            --initial-cluster docker-node1=http://192.168.37.130:2380,docker-node2=http://192.168.37.131:2380 \
//            --initial-cluster-state new&
//
//            http://docker-node1:2379,http://docker-node1:4001
//
//
//
//ETCD_NAME=docker-node1
//ETCD_DATA_DIR="/var/lib/etcd/etcd1"
//ETCD_LISTEN_PEER_URLS="http://192.168.37.130:2380"
//ETCD_LISTEN_CLIENT_URLS="http://0.0.0.0:2379,http://0.0.0.0:4001"
//ETCD_INITIAL_ADVERTISE_PEER_URLS="http://192.168.37.130:2380"
//ETCD_INITIAL_CLUSTER="docker-node1=http://docker-node1:2380,docker-node2=http://docker-node2:2380"
//ETCD_INITIAL_CLUSTER_STATE="new"
//ETCD_INITIAL_CLUSTER_TOKEN="my-etcd-cluster"
//ETCD_ADVERTISE_CLIENT_URLS="http://192.168.37.130:2379,http://192.168.37.130:4001"