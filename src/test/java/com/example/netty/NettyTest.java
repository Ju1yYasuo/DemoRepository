package com.example.netty;

import java.util.concurrent.TimeUnit;

/**
 * @author luox
 * @date 2022/4/29
 */
public class NettyTest {

    public static void main( String[] args )
    {
        //开启服务，服务会阻塞，所以使用一个线程开启
        new Thread(new Runnable() {
            @Override
            public void run() {
                NettyServer server = new NettyServer();
                server.bind(7777);
            }
        }).start();

        NettyClient client = new NettyClient();
        for (int i=0;i<3;i++){
            sleep();
            client.connect("127.0.0.1",7777);
            System.out.println("------");
        }
    }

    //休眠3秒
    public static void sleep(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
