package cn.demo.dfs.mongo.dao;

import cn.demo.dfs.mongo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {
    @Autowired
    MongoTemplate mongoTemplate;
    public void insert(){
        Account account = new Account();
        account.setUserName("admin");
        account.setPassWord("admin123");
        mongoTemplate.insert(account);
    }
}
