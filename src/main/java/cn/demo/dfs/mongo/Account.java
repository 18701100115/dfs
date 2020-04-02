package cn.demo.dfs.mongo;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by summer on 2017/5/5.
 */

@Document(collection = "account")
public class Account implements Serializable {
    private static final Long serialVersionUID = -3258839839160856613L;
    private String id;
    private String userName;
    private String passWord;
    @Indexed(name = "create_index",expireAfterSeconds =  0)
    private Date createTime;

    public Account(String id, String userName, String passWord, Date createTime) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.createTime = createTime;
    }

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}