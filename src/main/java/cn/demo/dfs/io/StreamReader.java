package cn.demo.dfs.io;

import java.io.*;

public class StreamReader {
    public static void main(String[] args) throws  Exception{
        File f = new File("D:"+File.separator+"a.txt");
        OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(f),"GBK");
        output.write("你好");
        output.close();
        InputStreamReader input = new InputStreamReader(new FileInputStream(f),"GBK");
        char[] chars = new char[100];
        int len = 0;
        while((len=input.read(chars))!=-1){
            System.out.println(new String(chars));
        }

    }
}
