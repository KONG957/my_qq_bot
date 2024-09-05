package love.baihao.listener.service;

import love.baihao.dto.MessageEventDTO;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.listener.service
 * @className: MessageListenerService
 * @author: bh
 * @description: TODO
 * @date: 2024/9/4 09:55
 * @version: 1.0
 */
public interface MessageListenerService {
    void listener(MessageEventDTO messageEventDTO);
}
