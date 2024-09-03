package love.baihao.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.config
 * @className: HttpConfigration
 * @author: bh
 * @description: TODO
 * @date: 2024/8/30 11:33
 * @version: 1.0
 */
@Configuration
public class HttpConfiguration {

    @Bean
    public HttpClient httpServer() {
        return HttpClients.createDefault();
    }

}
