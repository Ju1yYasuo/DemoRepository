package com.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Netty服务器类，用于接收请求
 */
public class NettyServer {


    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            NettyServer server = new NettyServer();
            server.bind(7777);
        }).start();

        //发送消息
        List<String> mesList = new ArrayList<>();
        mesList.add("消息1");
        mesList.add("消息2");
        mesList.add("消息3");
        int i = 0;
        while(true){
            if(i >= 3){
                i = 0;
            }
            TimeUnit.SECONDS.sleep(2);
            if(NettyServerHandler.map.size() != 0){
                for(Map.Entry<String, ChannelHandlerContext> entry : NettyServerHandler.map.entrySet()){
                    ByteBuf respByteBuf = Unpooled.copiedBuffer(mesList.get(i).getBytes());
                    //entry.getValue().write(respByteBuf);
                    entry.getValue().writeAndFlush(respByteBuf);
                }
            }

            i++;
        }


    }


    /**
     * 启动服务
     */
    public void bind(int port){
        //Reactor线程组，一个用来处理连接，一个用来处理网络读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            //启动NIO服务端的辅助启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //绑定线程组
            serverBootstrap.group(bossGroup,workGroup)
                    //指定通道类型（服务端是NioServerSocketChannel）
                    .channel(NioServerSocketChannel.class)
                    //设置通道的处理器
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //子通道
                    .childHandler(new ChildChannelHandler());
            //绑定并监听端口
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println(Thread.currentThread().getName()+"，启动成功，等待请求中");
            //future.channel()获取程序的channel,等待结束(closeFuthre),阻塞(sync)
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //退出，释放资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }


    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            //ChannelPipeline是一个链式的处理请求的流程
            arg0.pipeline().addLast(new NettyServerHandler());
        }
    }
}
