package org.netty.learn.net.base.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleNettyClient {
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);

    public void connect(String host,int port)throws Exception{
        try {
            Bootstrap bossGroup = new Bootstrap();
            bossGroup.group(eventLoopGroup); // (2)
            bossGroup.channel(NioSocketChannel.class);// (3)
            bossGroup.option(ChannelOption.TCP_NODELAY, true); // (4)
            bossGroup.handler(new RpcClientInitializer ()); //与server不同的是，这不是childhander

            ChannelFuture f = bossGroup.connect(host,port).sync(); //异步连接
            f.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new SimpleNettyClient().connect("127.0.0.1", 9999);
    }

}
