package cn.demo.dfs.io;

import cn.demo.dfs.hbase.User;
import com.alibaba.fastjson.JSON;

import java.io.*;

public class ObjectStreamDemo {
    public static void main(String[] args) throws Exception{
        File f = new File("D:"+File.separator+"user.ser");
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(f));
        output.writeObject(new User("1","2","3","3","3","3","3"));
        output.close();
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(f));
        User user = (User) input.readObject();
        System.out.println(JSON.toJSONString(user));


    }
}
