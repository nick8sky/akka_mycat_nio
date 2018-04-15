package org.netty.learn.net.base.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RPC Server
 *
 */
public class RpcServer {

    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private EventLoopGroup bossGroup = null; //reactor组，类似于selector + invoker
    private EventLoopGroup workerGroup = null; //网络读写

    public void stop() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }

    public void start() throws Exception {
        if (bossGroup == null && workerGroup == null) {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap(); //启动一个服务

            bootstrap.group(bossGroup, workerGroup) //两个组作为属性
                    .channel(NioServerSocketChannel.class) //绑定channel类型为server，nio中的ServerSocketChannel的子类
                    .childHandler(new RpcServerInitializer()) //指定IO事件的处理类，这里没有直接使用hander，@see RpcServerInitializer
                    .option(ChannelOption.SO_BACKLOG, 128)//对应的是tcp/ip协议listen函数中的backlog参数,服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true); //是否为keeplive

            ChannelFuture f = bootstrap.bind(9999).sync(); // (7)类似于Future，用于异步操作的通知回调。
            f.channel().closeFuture().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new RpcServer().start();
    }

}
