package com.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Netty server 处理类
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 保存id和容器的关系
    public static Map<String, ChannelHandlerContext> map = new HashMap<>();


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        ChannelId channelId = channel.id();
        map.put(channelId.toString(), ctx);

        ByteBuf respByteBuf = Unpooled.copiedBuffer("channelActive".getBytes());
        ctx.writeAndFlush(respByteBuf);
        //ctx.channel().writeAndFlush(respByteBuf);
    }

    /**
     * 接收客户端消息，自动触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //转换msg成Netty的ByteBuf对象，类似ByteBuffer（缓冲区）
        ByteBuf buf = (ByteBuf) msg;
        //创建缓冲区内信息大小的byte类型数组
        byte[] reg = new byte[buf.readableBytes()];
        //读取缓冲区的信息并转换成字符串
        buf.readBytes(reg);
        String mess = new String(reg,"UTF-8");
        System.out.println("服务端收到：" + mess);
        //回复消息
        //Scanner scanner = new Scanner(System.in);
        //String str = "";
        //if(scanner.hasNext()){
        //    str = scanner.next();
        //}
        //String respMess = "服务端回复收到,str:" + str;

        String respMess = "服务端回复收到,str:";
        //byte[] sendMesByte = sendMess.getBytes("UTF-8");
        //ByteBuf sendByteBuf = Unpooled.buffer(sendMesByte.length);
        //sendByteBuf.writeBytes(sendMesByte);

        //String respMess = "服务端回复收到";
        ByteBuf respByteBuf = Unpooled.copiedBuffer(respMess.getBytes());
        ctx.writeAndFlush(respByteBuf);
        //ctx.write(respByteBuf);
    }

    /**
     * 最后一次读取信息时触发
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //读完后刷新缓冲区，发送
        ctx.flush();
    }

    /**
     * 异常发生
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发送异常关闭context,释放相关资源
        ctx.close();
    }
}

