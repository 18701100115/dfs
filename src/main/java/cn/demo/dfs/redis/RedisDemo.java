package cn.demo.dfs.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisDemo extends Jedis{

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.37.131",6379);
        //======================HASH==================================
        Map<String,String> userMap = new HashMap<String, String>();
        userMap.put("userId","1");
        userMap.put("userName","张三");
        userMap.put("userAccount","zhangsan");
        userMap.put("passWord","admin123");
        userMap.put("email","admin123@163.com");
        jedis.hmset("userMap",userMap);
        Map<String,String> resultUserMap = jedis.hgetAll("userMap");//取出全部的MAP
        System.out.println("hgetAll,"+ JSON.toJSONString(resultUserMap));
        String email = jedis.hget("userMap","email");
        Boolean flag = jedis.hexists("userMap","email");
        System.out.println("email="+email);
        System.out.println("emailFLAG="+flag);
        jedis.hdel("userMap","email");//删除字段
        email = jedis.hget("userMap","email");
        flag = jedis.hexists("userMap","email");//字段是否存在
        System.out.println("email="+email);
        System.out.println("emailFLAG="+flag);
        resultUserMap = jedis.hgetAll("userMap");//取出全部的MAP
        System.out.println("hgetAll,"+ JSON.toJSONString(resultUserMap));

        //======================SET==================================
        jedis.sadd("s01","a","b","d","gg","11");
        jedis.sadd("s02","c","a","cc");
        jedis.sadd("s03","a","c","e");
        Long count = jedis.scard("s01");//set成员数量
        System.out.println("set成员数量,"+count);
        Set<String> sdiffList = jedis.sdiff("s01","s02","s03");//求差集 求第一个KEY的差集
        for(String str : sdiffList){
            System.out.println(str);
        }
        Set<String> sinterList = jedis.sinter("s01","s02","s03");//求并集 求三个key的共同并集
        for(String str : sinterList){
            System.out.println(str);
        }
        Set<String> unionList = jedis.sunion("s01","s02","s03"); //合集去重
        for(String str : unionList){
            System.out.println(str);
        }


        //======================SET SORTED==================================
        Map<String,Double> fenshuMap = new HashMap<String,Double>();
        fenshuMap.put("张三",10d);
        fenshuMap.put("李四",100d);
        fenshuMap.put("小明",70d);
        fenshuMap.put("小航",70d);
        fenshuMap.put("小红",30d);
        fenshuMap.put("小强",90d);
        fenshuMap.put("小卢",60d);
        jedis.zadd("fenshu",fenshuMap);
        jedis.zadd("fenshu",95d,"小李");
        Set<String> zrangeList = jedis.zrange("fenshu",0,-1);//从小到大查询排序列表
        for(String str : zrangeList){
            System.out.println(str);
        }
        Set<Tuple> tupleSet =  jedis.zrangeWithScores("fenshu",0,-1);//从小到大查询排序列表
        for(Tuple tuple : tupleSet){
            System.out.println(tuple.getElement()+"==="+tuple.getScore());
        }

        Set<String> zrevrangeList = jedis.zrevrange("fenshu",0,-1);//从大到小查询排序列表
        for(String str : zrevrangeList){
            System.out.println(str);
        }
        Set<Tuple> zrevtupleSet =  jedis.zrevrangeWithScores("fenshu",0,-1);//从大到小查询排序列表
        for(Tuple tuple : zrevtupleSet){
            System.out.println(tuple.getElement()+"==="+tuple.getScore());
        }
        Set<Tuple>  tuples = jedis.zrangeByScoreWithScores("fenshu",60d,100d);
        System.out.println("60分以上的同学从小到大");
        for(Tuple tuple : tuples){
            System.out.println(tuple.getElement()+"==="+tuple.getScore());
        }
        Set<Tuple>  revtuples = jedis.zrevrangeByScoreWithScores("fenshu",100d,60d);
        System.out.println("60分以上的同学从大到小");
        for(Tuple tuple : revtuples){
            System.out.println(tuple.getElement()+"==="+tuple.getScore());
        }
        
        jedis.set("t01","02");
        Transaction tr = jedis.multi();//事务
        tr.set("t02","02");
        tr.set("t03","02");
        tr.set("t04","02");
        tr.set("t05","02");
        List<Object> l = tr.exec();
        System.out.println(JSONArray.toJSONString(l));
    }

}
