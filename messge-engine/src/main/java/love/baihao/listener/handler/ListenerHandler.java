package love.baihao.listener.handler;

import love.baihao.dto.MessageEventDTO;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.listener.service
 * @className: MessageListenerHandler
 * @author: bh
 * @description: TODO
 * @date: 2024/9/4 09:51
 * @version: 1.0
 */
public interface ListenerHandler {

    public void handler(MessageEventDTO eventDTO);

}
