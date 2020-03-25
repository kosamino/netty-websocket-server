package bat.ke.qq.com.web.websocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("channelInitializer")
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Resource(name="httpRequestHandler")
	public ChannelHandler httpRequestHandler;
	@Resource(name="webSocketServerHandler")
	public ChannelHandler webSocketServerHandler;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("http-codec", new HttpServerCodec()); // HTTP编码解码器
		ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536)); // 把HTTP头、HTTP体拼成完整的HTTP请求
		ch.pipeline().addLast("http-handler", httpRequestHandler);
		ch.pipeline().addLast("websocket-handler",webSocketServerHandler);
	}

}
