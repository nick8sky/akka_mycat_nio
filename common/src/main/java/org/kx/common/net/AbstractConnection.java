package org.kx.common.net;



import lombok.Data;
import org.kx.common.NIOHandler;
import org.kx.common.processer.NIOProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NetworkChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * create by sunkx on 2018/3/28
 * 把Java的NetworkChannel进行封装
 */
@Data
public abstract class AbstractConnection implements NIOConnection {
    protected static final Logger LOGGER = LoggerFactory.getLogger(NIOSocketWR.class);

    protected NIOHandler handler; //NIOHandler是处理AbstractConnection读取的数据的处理方法类
    protected final AtomicBoolean isClosed;
    protected final NetworkChannel channel;
    protected final NIOSocketWR socketWR;
    protected NIOProcessor processor;

    protected volatile ByteBuffer  readBuffer;
    protected volatile ByteBuffer writeBuffer;

    protected long netInBytes;
    protected long netOutBytes;

    protected volatile int readBufferOffset;

    public AbstractConnection(NetworkChannel channel) {
        this.channel = channel;
        socketWR = new NIOSocketWR(this);
        this.isClosed = new AtomicBoolean(false);

    }



    /**
     * 读取可能的Socket字节流
     */
    public void onReadData(int got) throws IOException {

        if (isClosed.get()) {
            return;
        }

    }


}
