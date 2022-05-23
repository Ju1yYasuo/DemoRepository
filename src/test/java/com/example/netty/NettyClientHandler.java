package com.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;

/**
 * Netty client处理类
 *
 * @author luox
 * @date 2022/4/29
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext ctx;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void sendMsg(String str) {
        byte[] data = str.getBytes();
        ByteBuf firstMessage = Unpooled.buffer();
        firstMessage.writeBytes(data);
        ctx.writeAndFlush(firstMessage);
    }

    /**
     * 连接成功之后执行
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        //Scanner scanner = new Scanner(System.in);
        //String str = "";
        //if(scanner.hasNext()){
        //    str = scanner.next();
        //}
        //String sendMess = "我是客户端："+Thread.currentThread().getName() + ",str:" + str;

        String sendMess = "我是客户端："+Thread.currentThread().getName() + userName;
        byte[] sendMesByte = sendMess.getBytes("UTF-8");
        ByteBuf sendByteBuf = Unpooled.buffer(sendMesByte.length);
        sendByteBuf.writeBytes(sendMesByte);
        ctx.writeAndFlush(sendByteBuf);
    }

    /**
     * 接收返回的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String mess = new String(req,"UTF-8");
        System.out.println(Thread.currentThread().getName() + userName +"客户端接收到返回的消息："+mess);

        //String sendMess = "我是客户端："+Thread.currentThread().getName();
        //byte[] sendMesByte = sendMess.getBytes("UTF-8");
        //ByteBuf sendByteBuf = Unpooled.buffer(sendMesByte.length);
        //sendByteBuf.writeBytes(sendMesByte);
        //ctx.writeAndFlush(sendByteBuf);
        //ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

