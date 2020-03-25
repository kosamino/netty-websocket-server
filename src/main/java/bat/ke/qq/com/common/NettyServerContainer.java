package bat.ke.qq.com.common;

import bat.ke.qq.com.dao.GroupInfoDao;
import bat.ke.qq.com.dao.UserInfoDao;
import bat.ke.qq.com.web.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("singleton")
public class NettyServerContainer {

    private final Logger logger = LoggerFactory.getLogger(NettyServerContainer.class);

    private Thread nettyThread;
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private GroupInfoDao groupDao;
    /**
     *      启用一个新的线程监听webSocket端口
     *      1. 启动Netty WebSocket服务器；
     *      2. 加载用户数据；
     *      3. 加载用户交流群数据。
     */
    @PostConstruct
    public void init() {
        nettyThread = new Thread(webSocketServer);
        nettyThread.start();

        userInfoDao.loadUserInfo();
        groupDao.loadGroupInfo();
    }


    @SuppressWarnings("deprecation")
    @PreDestroy
    public void close() {
        logger.info("正在释放Netty Websocket相关连接...");
        webSocketServer.close();
        nettyThread.stop();//如果isInterrupted存在资源释放的问题 可以使用nettyThread.stop();
        logger.info("系统关闭！");
    }
}
