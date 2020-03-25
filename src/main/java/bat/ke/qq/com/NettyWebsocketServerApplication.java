package bat.ke.qq.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"bat.ke.qq.com"})
public class NettyWebsocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyWebsocketServerApplication.class, args);
    }

}
