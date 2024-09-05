package love.baihao.listener.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import love.baihao.annotation.SnakeToCamelCase;
import love.baihao.common.Response;
import love.baihao.dto.MessageEventDTO;
import love.baihao.listener.service.MessageListenerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.listener.controller
 * @className: MessgeListenerController
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 17:29
 * @version: 1.0
 */
@RestController("/")
public class MessageListenerController {

    @Resource
    private MessageListenerService messageListenerService;

    @PostMapping("/onebot")
    public Response listen(@SnakeToCamelCase  MessageEventDTO messageEventDTO) {
        messageListenerService.listener(messageEventDTO);
        return Response.success();
    }


}
