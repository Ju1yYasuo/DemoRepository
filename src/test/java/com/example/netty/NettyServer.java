package com.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty服务器类，用于接收请求
 */
public class NettyServer {

    /**
     * 启动服务
     * @param port 启动时绑定的端口
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
