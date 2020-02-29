package cn.demo.dfs.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

public class TestBuffer {
    @Test
    public void mark(){
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("abcde".getBytes());
        buf.flip();
        byte[] data = new byte[buf.limit()];
        buf.get(data,0,2);
        System.out.println(new String(data,0,data.length));
        System.out.println(buf.position());
        buf.mark();
        buf.get(data,2,2);
        System.out.println(new String(data,2,2));
        System.out.println(buf.position());
        buf.reset();
        System.out.println("reset之后的位置："+buf.position());
        System.out.println("缓冲区是否有可操作数据："+buf.hasRemaining());
        System.out.println("缓冲区可操作数据的数量："+buf.remaining());


        System.out.println(buf.position());




    }
    public static void main(String[] args) {

        ByteBuffer buf = ByteBuffer.allocate(1024);//缓冲区
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        buf.put("臭臭猪".getBytes());
        buf.put("臭臭猪12312312".getBytes());
        System.out.println("PUT之后的参数值");

        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println("切换读取数据模式");
        buf.flip();   //切换到读取模式
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println("读取缓冲区内容");
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println("缓冲区数据:"+new String(dst,0,dst.length));
        System.out.println("读取后参数");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.rewind(); //可重复读数据
        System.out.println("rewind后参数");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.clear();//清空缓冲区
        System.out.println("clear清空缓冲区后参数");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println("缓冲区数据:"+new String(dst,0,dst.length));

    }
}
