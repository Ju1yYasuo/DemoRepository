package com.example.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty Client
 *
 * @author luox
 * @date 2022/4/29
 */
public class NettyClient {

    /**
     * 连接目标服务器
     * @param host
     * @param port
     */
    public void connect(String host,int port){
        //NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChileHandler());
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            System.out.println(Thread.currentThread().getName()+",发起连接请求");
            //等待客户端链路关闭
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            group.shutdownGracefully();
        }
    }

    private class ChileHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new NettyClientHanlder());
        }
    }
}

