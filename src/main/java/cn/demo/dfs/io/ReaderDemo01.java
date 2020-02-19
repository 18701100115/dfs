package cn.demo.dfs.io;

import java.io.*;

public class ReaderDemo01 {
    public static void main(String[] args) throws  Exception{
        File f = new File("D:"+File.separator+"mysql.json");
        Reader reader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String text = null;

        File f1 = new File("E:"+File.separator+"mysql222222.json");
        Writer writer = new FileWriter(f1);
        BufferedWriter  bufferedWriter = new BufferedWriter(writer);
        while((text=bufferedReader.readLine())!=null){
//            System.out.println(text);
//            writer.write(text+"\n");
            bufferedWriter.write(text);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        writer.close();
        bufferedReader.close();
        reader.close();



        String s, s1 = new String();
        s = "你们" + "他们a我们" + "\n";
        System.out.println(s);
        /* A character stream whose source is a string. */
        StringReader in2 = new StringReader(s);
        int c;
        /*in2.read() Read a single character,(qixy: two bytes 或1个字节, so can handle chinese). or -1 if the end of the stream has been reached. Returns: The character read */
        while ((c = in2.read()) != -1) {
         System.out.println((char) c);
        }

        StringWriter stringWriter = new StringWriter();


    }
}
