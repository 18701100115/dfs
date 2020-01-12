package cn.demo.dfs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

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