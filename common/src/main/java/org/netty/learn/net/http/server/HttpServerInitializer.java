package org.netty.learn.net.http.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast("http-decoder",new HttpRequestDecoder());
        cp.addLast("http-aggregator",new HttpObjectAggregator(65536));
        cp.addLast("http-encoder",new HttpRequestEncoder( ));
        cp.addLast("http-chunked",new ChunkedWriteHandler( ));
        cp.addLast("fileServerHandler",new HttpServerHandler( ));

    }
}
