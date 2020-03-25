package bat.ke.qq.com.util;

import bat.ke.qq.com.model.po.GroupInfo;
import bat.ke.qq.com.model.po.UserInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {

    public static final String USER_TOKEN = "userId";
    
    public static Map<String, WebSocketServerHandshaker> webSocketHandshakerMap = 
            new ConcurrentHashMap<String, WebSocketServerHandshaker>();

	/**
	 * 用户对应连接的ChannelHandlerContext
	 */
	public static Map<String, ChannelHandlerContext> onlineUserMap = 
	        new ConcurrentHashMap<String, ChannelHandlerContext>();

	/**
	 * 组信息
	 */
	public static Map<String, GroupInfo> groupInfoMap =
	        new ConcurrentHashMap<String, GroupInfo>();

	/**
	 * 用户信息
	 */
	public static Map<String, UserInfo> userInfoMap =
	        new HashMap<String, UserInfo>();
}
