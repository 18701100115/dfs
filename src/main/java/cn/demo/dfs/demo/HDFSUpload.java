package cn.demo.dfs.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class HDFSUpload {
    private static InputStream input;
    private static OutputStream output;


    public static void main(String[] args) throws Exception{
        System.setProperty("HADOOP_USER_NAME","root");
        System.setProperty("hadoop.home.dir", "D:\\hadoop-3.1.2");
        System.out.println(System.getenv("HADOOP_HOME"));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://192.168.26.132:9000"), conf,"root");
//          fs.copyFromLocalFile(new Path("E:/mysql.json"),new Path("/aaa/2.mp3"));
//          System.out.println("上传完毕");
             fs.copyToLocalFile(new Path("/aaa/2.mp3"),new  Path("D:/mysql.json"));
             System.out.println("下载完毕");
                //fs.delete(new Path("/a·aa/2.mp3"),true);//删除文件


                fs.close();

    }

}
