package com.example.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Netty Client
 *
 * @author luox
 * @date 2022/4/29
 */
public class NettyClient {

    private NettyClientHandler nettyClientHandler;

    private int port;
    private String host;
    private String sendUserName;

    public static void main(String[] args) {

        NettyClient client = new NettyClient();
        client.connect("localhost",7777,"yasuo");
    }

    /**
     * 连接目标服务器
     */
    public void connect(String host,int port,String sendUserName){

        this.host = host;
        this.port = port;
        this.sendUserName = sendUserName;

        //NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    //.option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new ChileHandler());
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            System.out.println(Thread.currentThread().getName()+ sendUserName + ",客户端发起连接请求");

            if (channelFuture.isSuccess()) {
                TimeUnit.SECONDS.sleep(3);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Scanner sc = new Scanner(System.in);
                        //while (sc.hasNext()) {
                        //    nettyClientHandler.sendMsg(sc.next());
                        //}
                        nettyClientHandler.sendMsg(sendUserName + "客户端发送战斗邀请");
                    }
                }).start();
            }
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
            nettyClientHandler = new NettyClientHandler();
            nettyClientHandler.setUserName(sendUserName);
            socketChannel.pipeline().addLast(nettyClientHandler);
        }
    }
}

