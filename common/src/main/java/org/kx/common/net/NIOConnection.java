package org.kx.common.net;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * create by sunkx on 2018/3/28
 */
public interface NIOConnection extends ClosableConnection {

    //register  channel
    void register() throws IOException;

    //处理数据
    void handle(byte[] data);

    // 写出一块缓冲数据
    void write(ByteBuffer buffer);

    /**
     *
     * 所有NIO的通信需要在多路复用选择器上注册channel，这里有个对应的register()方法需要实现。然后，读取和写入数据都需要通过缓冲。
     * 缓冲区(Buffer)就是在内存中预留指定大小的存储空间用来对输入/输出(I/O)的数据作临时存储，这部分预留的内存空间就叫做缓冲区，使用缓冲区有这么两个好处：
     * 1. 减少实际的物理读写次数
     * 2. 缓冲区在创建时就被分配内存，这块内存区域一直被重用，可以减少动态分配和回收内存的次数
     * 读取到的数据需要经过处理，这里对应的就是handle(byte[])方法。
     */
}
