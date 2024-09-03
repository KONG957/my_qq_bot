package love.baihao.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.content
 * @className: MessageTypeEnum
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 20:25
 * @version: 1.0
 */
@AllArgsConstructor
@Getter
public enum MessageTypeEnum {
    PRIVATE("private"),
    GROUP("group"),;


    private String code;


}
