package love.baihao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.dto
 * @className: MessageEventDTO
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 18:01
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageEventDTO extends CommonEventDTO implements Serializable {
    /**
     * 消息类型 private, group
     */
    private String messageType;
    /**
     * 表示消息的子类型 group, public
     */
    private String subType;
    /**
     * 消息 ID
     */
    private Integer messageId;
    /**
     * 发送者 QQ 号
     */
    private Long userId;
    /**
     * 一个消息链
     */
    private List<MessageDTO> message;
    /**
     * CQ 码格式的消息
     */
    private String rawMessage;
    /**
     * 字体
     */
    private Integer font;
    /**
     * 发送者信息
     */
    private SenderDTO sender;

    /**
     * 请求类型 friend group
     */
    private String requestType;
    /**
     * 通知类型 group_upload group_admin.....
     */
    private String noticeType;


}
