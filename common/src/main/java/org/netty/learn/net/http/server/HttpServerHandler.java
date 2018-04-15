package org.netty.learn.net.http.server;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderValues;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspHeaderNames.*;

/**
 * RPC Handler（RPC request processor）
 */
public class HttpServerHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HTTP_1_1, OK, Unpooled.wrappedBuffer("OK OK OK OK"
                .getBytes()));
        response.headers().set(CONTENT_TYPE, "text/plain");
        response.headers().set(CONTENT_LENGTH, "16");
        response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        ctx.write(response);
        ctx.flush();
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }
}
