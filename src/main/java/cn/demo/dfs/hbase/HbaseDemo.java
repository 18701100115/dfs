//package cn.demo.dfs.hbase;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.*;
//import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class HbaseDemo {
//    private   Admin admin;
//    private   Connection connection;
//    @PostConstruct
//    public void init(){
//        Configuration configuration = HBaseConfiguration.create();
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//        configuration.set("hbase.zookeeper.quorum", "192.168.37.132");
//        //集群配置↓
//        //configuration.set("hbase.zookeeper.quorum", "101.236.39.141,101.236.46.114,101.236.46.113");
//        configuration.set("hbase.master", "192.168.37.132:16000");
//        try {
//              connection = ConnectionFactory.createConnection(configuration);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //创建表
//    public void createTable(String tableNmae) throws IOException {
//
//        TableName tableName = TableName.valueOf(tableNmae);
//        admin = connection.getAdmin();
//        if (admin.tableExists(tableName)) {
//            System.out.println("表已存在！");
//        } else {
//            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
////            for (String col : cols) {
//                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("data");
//                hTableDescriptor.addFamily(hColumnDescriptor);
////            }
//            admin.createTable(hTableDescriptor);
//        }
//    }
//
//    //插入数据
//    public void insertData(String tableName, Object obj) throws IOException {
//        TableName tablename = TableName.valueOf(tableName);
//        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
//        Put put = new Put(jsonObject.getString("id").getBytes());
//        for(String key : jsonObject.keySet()){
//            put.addColumn("data".getBytes(), key.getBytes(), jsonObject.getString(key).getBytes());
//        }
//        Table table = connection.getTable(tablename);
//        table.put(put);
//    }
//    //插入数据
//    public void insertDataList(String tableName, List objList) throws IOException {
//        TableName tablename = TableName.valueOf(tableName);
//        List<Put> putList = new ArrayList<Put>();
//        for(Object obj: objList){
//            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj));
//            Put put = new Put(jsonObject.getString("id").getBytes());
//        //参数：1.列族名  2.列名  3.值
////        put.addColumn("information".getBytes(), "username".getBytes(), user.getUsername().getBytes()) ;
////        put.addColumn("information".getBytes(), "age".getBytes(), user.getAge().getBytes()) ;
////        put.addColumn("information".getBytes(), "password".getBytes(), user.getPassword().getBytes()) ;
////        put.addColumn("information".getBytes(), "gender".getBytes(), user.getGender().getBytes()) ;
////        put.addColumn("contact".getBytes(), "phone".getBytes(), user.getPhone().getBytes());
//        for(String key : jsonObject.keySet()){
//            put.addColumn("data".getBytes(), key.getBytes(), jsonObject.getString(key).getBytes());
//
//        }
//            putList.add(put);
//        }
//        Table table = connection.getTable(tablename);
//        table.put(putList);
//    }
//
//    //获取原始数据
//    public ResultScanner getNoDealData(String tableName){
//        ResultScanner resutScanner = null;
//        try {
//            Table table= connection.getTable(TableName.valueOf(tableName));
//            Scan scan = new Scan();
//              resutScanner = table.getScanner(scan);
////            for(Result result: resutScanner){
////                System.out.println("scan:  " + result);
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return resutScanner;
//    }
//
//    //根据rowKey进行查询
//    public <T> T  getDataByRowKey(String tableName, String rowKey,Class<T> c) throws IOException {
//
//        Table table = connection.getTable(TableName.valueOf(tableName));
//        Get get = new Get(rowKey.getBytes());
//        User user = new User();
//        user.setId(rowKey);
//        JSONObject jsonObject = new JSONObject();
//
//        //先判断是否有此条数据
//        if(!get.isCheckExistenceOnly()){
//            Result result = table.get(get);
//            for (Cell cell : result.rawCells()){
//                String colName = Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
//                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
//                jsonObject.put(colName,value);
//            }
//        }
//        return JSONObject.parseObject(jsonObject.toJSONString(),c);
//    }
//
//    //查询指定单cell内容
//    public String getCellData(String tableName, String rowKey, String family, String col){
//
//        try {
//            Table table = connection.getTable(TableName.valueOf(tableName));
//            String result = null;
//            Get get = new Get(rowKey.getBytes());
//            if(!get.isCheckExistenceOnly()){
//                get.addColumn(Bytes.toBytes(family),Bytes.toBytes(col));
//                Result res = table.get(get);
//                byte[] resByte = res.getValue(Bytes.toBytes(family), Bytes.toBytes(col));
//                return result = Bytes.toString(resByte);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //查询指定表名中所有的数据
//    public <T> List<T>getAllData(String tableName,Class<T> c){
//
//        Table table = null;
//        List<JSONObject> list = new ArrayList<JSONObject>();
//        try {
//            table = connection.getTable(TableName.valueOf(tableName));
//            ResultScanner results = table.getScanner(new Scan());
//            for (Result result : results){
//                String id = new String(result.getRow());
//                System.out.println("用户名:" + new String(result.getRow()));
//                JSONObject jsonObject = new JSONObject();
//                for(Cell cell : result.rawCells()){
//                    String row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
//                    //String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
//                    String colName = Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
//                    String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
//                    jsonObject.put("id",row);
//                    jsonObject.put(colName,value);
//            }
//                list.add(jsonObject);
//            } }catch (IOException e) {
//            e.printStackTrace();
//        }
//        return JSONArray.parseArray(JSONArray.toJSONString(list),c);
//    }
//
//    //删除指定cell数据
//    public void deleteByRowKey(String tableName, String rowKey) throws IOException {
//
//        Table table = connection.getTable(TableName.valueOf(tableName));
//        Delete delete = new Delete(Bytes.toBytes(rowKey));
//        //删除指定列
//        //delete.addColumns(Bytes.toBytes("contact"), Bytes.toBytes("email"));
//        table.delete(delete);
//    }
//
//    //删除表
//    public void deleteTable(String tableName){
//
//        try {
//            TableName tablename = TableName.valueOf(tableName);
//            admin = connection.getAdmin();
//            admin.disableTable(tablename);
//            admin.deleteTable(tablename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}