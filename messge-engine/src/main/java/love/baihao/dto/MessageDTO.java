package love.baihao.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.dto
 * @className: Message
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 18:06
 * @version: 1.0
 */
@Data
public class MessageDTO {

    /**
     * 消息类型
     */
    private String type;

    private Map<String,Object> data;

}
