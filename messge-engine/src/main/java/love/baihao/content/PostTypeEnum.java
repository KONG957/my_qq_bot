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

    MESSAGE("message","messageListenerHandler"),
    MESSAGE_SENT("message_sent","messageSentListenerHandler"),
    REQUEST("request","requestListenerHandler"),
    NOTICE("notice","noticeListenerHandler"),
    META_EVENT("meta_event","metaEventListenerHandler"),;

    private String code;
    private String name;


    public static PostTypeEnum getByCode(String code) {
        for (PostTypeEnum postTypeEnum : PostTypeEnum.values()) {
            if (postTypeEnum.getCode().equals(code)) {
                return postTypeEnum;
            }
        }
        return null;
    }

}
