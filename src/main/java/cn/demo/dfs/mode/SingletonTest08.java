package cn.demo.dfs.mode;

import cn.demo.dfs.hbase.User;
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
@Component
public enum SingletonTest08 {
    INSTANCE;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    public String  sayHello(){
        System.out.println("sayHello");
        return "ok";
    }
    public void save(Object obj){
        String id = JSONObject.parseObject(JSON.toJSONString(obj)).getString("id");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(id)
                .withObject(obj)
                .build();
        elasticsearchTemplate.index(indexQuery);
    }
    public void addBatch(List objs){
        List<IndexQuery> list = new ArrayList<IndexQuery>();
        for(Object obj : objs){
            String id = JSONObject.parseObject(JSON.toJSONString(obj)).getString("id");
            list.add(new IndexQueryBuilder()
                    .withId(id)
                    .withObject(obj)
                    .build());
        }
        elasticsearchTemplate.bulkIndex(list);
    }
    public <T> List<T>  findByAll(Class<T> c){
        Pageable pageable = PageRequest.of(0,999);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                //.withQuery(QueryBuilders.matchPhraseQuery("name", "菜鸟"))
                .withPageable(pageable)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, c);

    }
    public static void main(String[] args) {
        SingletonTest08 s1 = SingletonTest08.INSTANCE;
        SingletonTest08 s2 = SingletonTest08.INSTANCE;
        System.out.println(s1==s2);
        System.out.println(s1.hashCode()==s2.hashCode());
        System.out.println(s1.sayHello());

    }
}
