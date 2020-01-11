package cn.demo.dfs.demo;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HiveJDBC {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://192.168.26.132:10000/hive_jdbc_test";
    private static String user = "root";
    private static String password = "";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private Object instance;
    public  void test(){
        System.out.println("递归调用...");
        test();
    }
    public static void main(String[] args) throws Exception {
//        Class.forName(driverName);
//        conn = DriverManager.getConnection(url,user,password);
//        stmt = conn.createStatement();
//        String sql = "create database hive_jdbc_test";
//        String sql = "create table emp(\n" +
//                "empno int,\n" +
//                "ename string,\n" +
//                "job string,\n" +
//                "mgr int,\n" +
//                "hiredate date,\n" +
//                "sal double,\n" +
//                "comm double,\n" +
//                "deptno int\n" +
//                ")\n" +
//                "row format delimited fields terminated by '\\t'";
//        String sql = "insert into emp values (3,'张三1','扫大街1',1,to_date('2019-10-10 09:55:54'),2.23,5.00,99)";
//        String sql = "load data local inpath '/home/hive_base.txt' overwrite into table emp";
//        String sql = " truncate table emp";//清楚表数据
//        String sql = "select * from emp";
//        System.out.println("Running: " + sql);
//        rs = stmt.executeQuery(sql);
//        while (rs.next()) {
//            System.out.println(rs.getString(1));
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
//            System.out.println(rs.getString(4));
//            System.out.println(rs.getString(5));
//            System.out.println(rs.getString(6));
//            System.out.println(rs.getString(7));
//        }
//        List l = new ArrayList();
//        while(true){
//            l.add(new HDFSUpload());
//        }
//            new HiveJDBC().test();

//        String s1 ="abc";
////        String s2 ="abc";
////        System.out.println(s1==s2);
////        String s3 = new String("abc");
////        System.out.println(s1==s3);
////        System.out.println(s1==s3.intern());
        System.out.println("start");
        HiveJDBC h1;
        HiveJDBC h2;
        h1 = new HiveJDBC();

        h2 = new HiveJDBC();
        for(int i=0;i<10000;i++) {


            h1.instance = h2;
            h2.instance = h1;


        }
        h1 = null;
        h2 = null;
        System.out.println("end=======================");
        System.gc();


    }
}
