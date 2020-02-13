package cn.demo.dfs.mode.singletion;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/***
 * 枚举单例模式
 *
 */
public enum SingletonTest08 {
    INSTANCE;
    SingletonTest08() { }
    public String  sayHello(){
        System.out.println("sayHello");
        return "ok";
    }
    public static void main(String[] args) {
        SingletonTest08 s1 = SingletonTest08.INSTANCE;
        SingletonTest08 s2 = SingletonTest08.INSTANCE;
        System.out.println(s1==s2);
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1.sayHello());

    }
}
