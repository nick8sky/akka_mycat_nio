package org.kx.common.buffer;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by sunkx on 2018/3/28
 */
public interface BufferPool {
    public ByteBuffer allocate(int size);  //分配
    public void recycle(ByteBuffer theBuf);  //回收
    public long capacity();
    public long size();
    public int getConReadBuferChunk();  //块
    public  int getSharedOptsCount();
    public int getChunkSize();
    public ConcurrentHashMap<Long,Long> getNetDirectMemoryUsage();
    public BufferArray allocateArray();
}
