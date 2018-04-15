package org.netty.learn.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;


/**
 * create by sunkx on 2018/3/28
 */
public class ServerChannelTest {

    private  void response(SocketChannel socketChannel ,Object msg) throws IOException {
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
    public   void start() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        Selector selector =  Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for(;;){
            /*SocketChannel socketChannel =  serverSocketChannel.accept();
            if(socketChannel != null){
                System.out.println("accept ------------" );
            }*/
            SelectionKey key =null;
            selector.select(1000);
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                key = selectedKeys.next();
                selectedKeys.remove();
                if(key.isAcceptable()){
                    System.out.println("get a connect ");
                    ServerSocketChannel serverAcceptChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverAcceptChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    // do next
                }
                if(key.isReadable()){
                    System.out.println("handle request ");
                    StringBuilder sb = new StringBuilder();
                    //Thread.sleep(500l);
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buf = ByteBuffer.allocate(48);
                    int bytesRead = socketChannel.read(buf);
                    while (bytesRead >0 ) {
                        buf.flip();

                        while(buf.hasRemaining()){
                            sb.append((char)buf.get());

                        }

                        buf.clear();
                        bytesRead = socketChannel.read(buf);
                    }
                    System.out.println(sb.toString());
                    response(socketChannel,"server"); //返回
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
