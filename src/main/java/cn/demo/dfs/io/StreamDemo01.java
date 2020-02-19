package cn.demo.dfs.io;

import java.io.*;

public class StreamDemo01 {
    public static void main(String[] args)throws Exception {
        File f = new File("D:"+File.separator+"mysql.json");
        InputStream inputStream = new FileInputStream(f);
         File f1 = new File("E:"+File.separator+"mysql111.json");
        OutputStream outputStream = new FileOutputStream(f1);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        int len = 0 ;
        byte[] bys = new byte[1024];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//        while((len = bufferedInputStream.read(bys)) != -1){
        while((len = inputStream.read(bys)) != -1){
            System.out.println(new String(bys,0,len));
            bufferedOutputStream.write(bys,0,len);
        }


        bufferedOutputStream.close();
        inputStream.close();
        outputStream.close();
    }
}
