package love.baihao.listener.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import love.baihao.content.PostTypeEnum;
import love.baihao.dto.MessageEventDTO;
import love.baihao.listener.handler.ListenerHandler;
import love.baihao.listener.service.MessageListenerService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.listener.service.impl
 * @className: MessageLisenerServiceImpl
 * @author: bh
 * @description: TODO
 * @date: 2024/9/4 09:56
 * @version: 1.0
 */
@Service
@Slf4j
public class MessageListenerServiceImpl implements MessageListenerService {

    private static final Map<String, ListenerHandler> HANDLER_MAP = new ConcurrentHashMap<>();

    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        Map<String, ListenerHandler> beansOfType = applicationContext.getBeansOfType(ListenerHandler.class);
        HANDLER_MAP.putAll(beansOfType);
    }

    @Override
    public void listener(MessageEventDTO messageEventDTO) {
        String postType = messageEventDTO.getPostType();
        PostTypeEnum postTypeEnum = PostTypeEnum.getByCode(postType);
        if(postTypeEnum == null) {
            log.error("事件类型不正确");
            return;
        }
        ListenerHandler listenerHandler = HANDLER_MAP.get(postTypeEnum.getName());
        listenerHandler.handler(messageEventDTO);
    }
}
