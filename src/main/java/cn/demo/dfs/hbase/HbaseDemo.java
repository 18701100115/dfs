package cn.demo.dfs.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HbaseDemo {
    private   Admin admin;
    private   Connection connection;
    @PostConstruct
    public void init(){
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "192.168.37.132");
        //集群配置↓
        //configuration.set("hbase.zookeeper.quorum", "101.236.39.141,101.236.46.114,101.236.46.113");
        configuration.set("hbase.master", "192.168.37.132:16000");
        try {
              connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建表
    public void createTable(String tableNmae, String[] cols) throws IOException {

        TableName tableName = TableName.valueOf(tableNmae);
        admin = connection.getAdmin();
        if (admin.tableExists(tableName)) {
            System.out.println("表已存在！");
        } else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
        }
    }

    //插入数据
    public void insertData(String tableName, User user) throws IOException {
        TableName tablename = TableName.valueOf(tableName);
        Put put = new Put(("user-" + user.getId()).getBytes());
        //参数：1.列族名  2.列名  3.值
        put.addColumn("information".getBytes(), "username".getBytes(), user.getUsername().getBytes()) ;
        put.addColumn("information".getBytes(), "age".getBytes(), user.getAge().getBytes()) ;
        put.addColumn("information".getBytes(), "gender".getBytes(), user.getGender().getBytes()) ;
        put.addColumn("contact".getBytes(), "phone".getBytes(), user.getPhone().getBytes());
        put.addColumn("contact".getBytes(), "email".getBytes(), user.getEmail().getBytes());
        //HTable table = new HTable(connection.getConfiguration(),tablename);已弃用
        Table table = connection.getTable(tablename);
        table.put(put);
    }

    //获取原始数据
    public ResultScanner getNoDealData(String tableName){
        ResultScanner resutScanner = null;
        try {
            Table table= connection.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
              resutScanner = table.getScanner(scan);
//            for(Result result: resutScanner){
//                System.out.println("scan:  " + result);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resutScanner;
    }

    //根据rowKey进行查询
    public User getDataByRowKey(String tableName, String rowKey) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        User user = new User();
        user.setId(rowKey);
        //先判断是否有此条数据
        if(!get.isCheckExistenceOnly()){
            Result result = table.get(get);
            for (Cell cell : result.rawCells()){
                String colName = Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                if(colName.equals("username")){
                    user.setUsername(value);
                }
                if(colName.equals("age")){
                    user.setAge(value);
                }
                if (colName.equals("gender")){
                    user.setGender(value);
                }
                if (colName.equals("phone")){
                    user.setPhone(value);
                }
                if (colName.equals("email")){
                    user.setEmail(value);
                }
            }
        }
        return user;
    }

    //查询指定单cell内容
    public String getCellData(String tableName, String rowKey, String family, String col){

        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            String result = null;
            Get get = new Get(rowKey.getBytes());
            if(!get.isCheckExistenceOnly()){
                get.addColumn(Bytes.toBytes(family),Bytes.toBytes(col));
                Result res = table.get(get);
                byte[] resByte = res.getValue(Bytes.toBytes(family), Bytes.toBytes(col));
                return result = Bytes.toString(resByte);
            }else{
                return result = "查询结果不存在";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "出现异常";
    }

    //查询指定表名中所有的数据
    public List<User> getAllData(String tableName){

        Table table = null;
        List<User> list = new ArrayList<User>();
        try {
            table = connection.getTable(TableName.valueOf(tableName));
            ResultScanner results = table.getScanner(new Scan());
            User user = null;
            for (Result result : results){
                String id = new String(result.getRow());
                System.out.println("用户名:" + new String(result.getRow()));
                user = new User();
                for(Cell cell : result.rawCells()){
                    String row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                    //String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
                    String colName = Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
                    String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    user.setId(row);
                    if(colName.equals("username")){
                        user.setUsername(value);
                    }
                    if(colName.equals("age")){
                        user.setAge(value);
                    }
                    if (colName.equals("gender")){
                        user.setGender(value);
                    }
                    if (colName.equals("phone")){
                        user.setPhone(value);
                    }
                    if (colName.equals("email")){
                        user.setEmail(value);
                    }
                    if (colName.equals("password")){
                        user.setPassword(value);
                    }
                }
                list.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //删除指定cell数据
    public void deleteByRowKey(String tableName, String rowKey) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除指定列
        //delete.addColumns(Bytes.toBytes("contact"), Bytes.toBytes("email"));
        table.delete(delete);
    }

    //删除表
    public void deleteTable(String tableName){

        try {
            TableName tablename = TableName.valueOf(tableName);
            admin = connection.getAdmin();
            admin.disableTable(tablename);
            admin.deleteTable(tablename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}