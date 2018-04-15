package org.netty.learn.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;


/**
 * create by sunkx on 2018/3/28
 */
public class Commontest {


    @Test
    public   void read() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/sunkaixiang/IdeaProjects/mycat_nio/common/src/main/java/org/netty/learn/Commontest.java", "rw");
        FileChannel inChannel = aFile.getChannel();
        inChannel.position(153);
        // inChannel.truncate(153);
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
    @Test
    public   void write() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/sunkaixiang/IdeaProjects/mycat_nio/common/src/main/java/org/netty/learn/write.java", "rw");
        String newData = "New String to write to file..." + System.currentTimeMillis();
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();
        while(buf.hasRemaining()) {
            inChannel.write(buf);
        }
    }

    private  void sendRequest(SocketChannel socketChannel ,Object msg) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(msg.toString().getBytes());

        buf.flip();

        while(buf.hasRemaining()) {
            socketChannel.write(buf);
        }
        System.out.println("send a msg");
    }

    @Test
    public   void client() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        Selector selector =  Selector.open();
        socketChannel.configureBlocking(false);
        if(socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            sendRequest(socketChannel,new Date());
        }else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        //建立连接之后，监听消息
        for(;;){
            SelectionKey key =null;
            selector.select(1000);
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                key = selectedKeys.next();
                selectedKeys.remove();
                if(key.isConnectable()){ //连接成功
                    System.out.println("make a connect ");
                    SocketChannel connectChannel  = (SocketChannel) key.channel();
                    if(connectChannel.finishConnect()){
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        sendRequest(socketChannel,"client");
                    }
                    // do next
                }
                if(key.isReadable()){
                    System.out.println("handle response ");
                    StringBuilder sb = new StringBuilder();
                    SocketChannel magChannel = (SocketChannel) key.channel();
                    ByteBuffer buf = ByteBuffer.allocate(48);
                    int bytesRead = socketChannel.read(buf);
                    while (bytesRead >0) {
                        buf.flip();

                        while(buf.hasRemaining()){
                            sb.append((char)buf.get());
                        }

                        buf.clear();
                        bytesRead = socketChannel.read(buf);
                    }
                    System.out.println(sb.toString());
                    //response(socketChannel,new Date()); //不返回
                    //关闭 channel
                    if(bytesRead <0){
                        key.cancel();
                        socketChannel.close();
                    }

                }

            }
        }



    }




}
