package love.baihao.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.interceptor
 * @className: WebConfig
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 21:11
 * @version: 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0, new CustomArgumentResolver());
    }
}
