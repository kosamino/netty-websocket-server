package bat.ke.qq.com.web.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WebSocketServer implements Runnable{

    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;

	private ServerBootstrap serverBootstrap;
	
	private int port=3333;

	@Resource(name = "channelInitializer")
	private ChannelHandler channelInitializer;

	private ChannelFuture serverChannelFuture;
	
	public WebSocketServer() {
	    
	}

	@Override
	public void run() {
        build();
	}
	
	/**
	 * 描述：启动Netty Websocket服务器
	 */
	public void build() {
		try {
			bossGroup=new NioEventLoopGroup();
			workerGroup=new NioEventLoopGroup();
			serverBootstrap=new ServerBootstrap();

			serverBootstrap.group(bossGroup, workerGroup)
						   .channel(NioServerSocketChannel.class)
						   .option(ChannelOption.SO_BACKLOG, 1024)
						   .option(ChannelOption.TCP_NODELAY, true)
						   .childOption(ChannelOption.SO_KEEPALIVE, true)
						   .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(592048))//配置固定长度接收缓存区分配器
						   .childHandler(channelInitializer);

	        logger.info("Netty Websocket服务器启动完成已绑定端口:" + port);
			
	        serverChannelFuture = serverBootstrap.bind(port).sync();
		} catch (Exception e) {
		    logger.info(e.getMessage());
			bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            e.printStackTrace();
		}

	}

	public void close(){
	    serverChannelFuture.channel().close();
		Future<?> bossGroupFuture = bossGroup.shutdownGracefully();
        Future<?> workerGroupFuture = workerGroup.shutdownGracefully();
        
        try {
            bossGroupFuture.await();
            workerGroupFuture.await();
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
        }
	}

}
