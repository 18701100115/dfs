package cn.demo.dfs.utils;

import cn.demo.dfs.hbase.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class EsUtils {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    public void save(Object obj){
        String id = JSONObject.parseObject(JSON.toJSONString(obj)).getString("id");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(id)
                .withObject(obj)
                .build();
        elasticsearchTemplate.index(indexQuery);
    }
    public void addBatch(List<Object> objs){
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
}
