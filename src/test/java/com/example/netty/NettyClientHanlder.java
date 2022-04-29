package com.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Netty client处理类
 *
 * @author luox
 * @date 2022/4/29
 */
public class NettyClientHanlder extends ChannelInboundHandlerAdapter {

    /**
     * 连接成功之后执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String sendMess = "我是客户端："+Thread.currentThread().getName();
        byte[] sendMesByte = sendMess.getBytes("UTF-8");
        ByteBuf sendByteBuf = Unpooled.buffer(sendMesByte.length);
        sendByteBuf.writeBytes(sendMesByte);
        ctx.writeAndFlush(sendByteBuf);
    }

    /**
     * 接收返回的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String mess = new String(req,"UTF-8");
        System.out.println(Thread.currentThread().getName()+"接收到返回的消息："+mess);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

