package org.kx.common.net;

import ch.qos.logback.core.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * create by sunkx on 2018/3/28
 */
public   class NIOSocketWR  {
    protected static final Logger LOGGER = LoggerFactory.getLogger(NIOSocketWR.class);

    private SelectionKey selectionKey;
    private final AbstractConnection con;
    private final SocketChannel channel;

    private final AtomicBoolean writing = new AtomicBoolean(false);


    public NIOSocketWR(AbstractConnection con) {
        this.con = con;
        this.channel = (SocketChannel) con.getChannel();
    }

    public void register(Selector selector) throws IOException {
        try {
            selectionKey = channel.register(selector, SelectionKey.OP_READ, con);  //注册一个read事件
        } finally {
            clearSelectionKey();
        }
    }


    private void clearSelectionKey() {
        try {
            SelectionKey key = this.selectionKey;
            if (key != null && key.isValid()) {
                key.attach(null);
                key.cancel();
            }
        } catch (Exception e) {
            LOGGER.warn("clear selector keys err:" + e);
        }
    }


    public void asynRead() throws IOException {
        ByteBuffer theBuffer = con.getReadBuffer();
        if (theBuffer == null) {

            theBuffer = con.getProcessor().getBufferPool().allocate(con.getProcessor().getBufferPool().getChunkSize());

            con.setReadBuffer(theBuffer);
        }

        int got = channel.read(theBuffer);

        con.onReadData(got);
    }


    public void doNextWriteCheck() {


    }



}
