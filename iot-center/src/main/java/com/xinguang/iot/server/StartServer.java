package com.xinguang.iot.server;

import io.netty.channel.ChannelFuture;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动服务器，主线程所在
 * 
 * @author zer0
 * @version 1.0
 * @date 2015-2-14
 */
public class StartServer {
	
	public static void main(String[] args){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		context.registerShutdownHook();


		final TcpServer tcpServer = new TcpServer();
		ChannelFuture future = tcpServer.startServer();

		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				tcpServer.destory();
			}
		});
		future.channel().closeFuture().syncUninterruptibly();

	}
}
