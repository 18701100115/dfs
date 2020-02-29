package cn.demo.dfs.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 直接缓冲区于非直接缓冲区
 */
public class TestChannel {
//    @Test
    public void test1()throws  Exception{
        File inFile = new File("D:"+File.separator+"timg.gif");
        File outFile = new File("E:"+File.separator+"upload3.gif");
        FileInputStream in = new FileInputStream(inFile);
        FileOutputStream out = new FileOutputStream(outFile);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024*60);
        while(inChannel.read(buffer)!=-1){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        outChannel.close();
        inChannel.close();
        out.close();
        in.close();
    }

    /**
     * 使用屋里内存
     * @throws Exception
     */
    @Test
    public void test2()throws  Exception{
     FileChannel inChannel = FileChannel.open(Paths.get("D:"+File.separator+"timg.gif"),StandardOpenOption.READ);
     FileChannel outChannel = FileChannel.open(Paths.get("D:"+File.separator+"timg01.gif"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
     MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
     MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());
     byte[] bytes = new byte[inMappedByteBuffer.limit()];
     inMappedByteBuffer.get(bytes);
     outMappedByteBuffer.put(bytes);
    }
    @Test
    public void test3()throws  Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("D:"+File.separator+"timg.gif"),StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:"+File.separator+"timg02.gif"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
        inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.close();
        inChannel.close();

    }


    @Test
    public void test4()throws Exception{
        File inFile = new File("D:"+File.separator+"mysql.json");
        RandomAccessFile raf1 = new RandomAccessFile(inFile,"rw");
        FileChannel inFileChannel = raf1.getChannel();
        ByteBuffer buf1 = ByteBuffer.allocate(1024);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers = {buf1,buf2};
        inFileChannel.read(byteBuffers);
        for(ByteBuffer buffer : byteBuffers){
            buffer.flip();
        }
        System.out.println(new String(byteBuffers[0].array(),0,byteBuffers[0].limit()));
        System.out.println("===============================");
        System.out.println(new String(byteBuffers[1].array(),0,byteBuffers[1].limit()));//分散读取

        File outFile = new File("E:"+File.separator+"upload_mysql.json");
        RandomAccessFile raf2 = new RandomAccessFile(outFile,"rw");
        FileChannel outFileChannel = raf2.getChannel();
        outFileChannel.write(byteBuffers);
        outFileChannel.close();
        raf2.close();;
        inFileChannel.close();
        raf1.close();;



    }
   }
