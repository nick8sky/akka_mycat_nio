package org.netty.learn.net.base.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import java.io.UnsupportedEncodingException;

/**
 */
public class RpcClientHandler extends ChannelHandlerAdapter {
    private final ByteBuf msg;

    public RpcClientHandler() {
        msg = Unpooled.buffer();
        msg.writeBytes("hello netty .".getBytes());
    }

    /**
     * 客户端和服务端连接成功后，会调用channelActive
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("connect succeed !");
        ctx.writeAndFlush(msg);//发送消息
    }

    /**
     * 服务器应答
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf bf = (ByteBuf) msg;
        byte[] req = new byte[bf.readableBytes()];
        bf.readBytes(req);
        String str = new String(req, "utf-8");
        System.out.println("client read from server :" + str);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable ex) {
        ctx.close();
    }
}

