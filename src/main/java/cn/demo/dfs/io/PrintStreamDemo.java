package cn.demo.dfs.io;

import java.io.File;
import java.io.PrintStream;

public class PrintStreamDemo {
    public static void main(String[] args) throws  Exception{
        File f = new File("D:"+File.separator+"sss.txt");
        PrintStream printStream = new PrintStream(f);
        printStream.println("你好");
    }
}
