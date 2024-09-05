package love.baihao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.dto
 * @className: CommonEventDTO
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 18:00
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonEventDTO {
    /**
     * 事件发生的unix时间戳
     */
    private Long time;
    /**
     * 收到事件的机器人的 QQ 号
     */
    private Long selfId;
    /**
     * 事表示该上报的类型, 消息, 消息发送, 请求, 通知, 或元事件
     */
    private String postType;

}
