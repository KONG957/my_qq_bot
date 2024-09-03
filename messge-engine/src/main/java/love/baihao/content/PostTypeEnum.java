package love.baihao.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.eume
 * @className: MessageTypeEume
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 20:18
 * @version: 1.0
 */
@AllArgsConstructor
@Getter
public enum PostTypeEnum {

    MESSAGE("message"),
    MESSAGE_SENT("message_sent"),
    REQUEST("request"),
    NOTICE("notice"),
    META_EVENT("meta_event"),;

    private String code;


}
