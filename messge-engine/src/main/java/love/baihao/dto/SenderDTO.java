package love.baihao.dto;

import lombok.Data;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.dto
 * @className: SenderDTO
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 20:34
 * @version: 1.0
 */
@Data
public class SenderDTO {

    /**
     * 发送者 QQ 号
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别, male 或 female 或 unknown
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 群名片／备注
     */
    private String card;
    /**
     * 角色, owner 或 admin 或 member
     */
    private String role;
    /**
     * 临时群消息来源群号
     */
    private Long groupId;
    /**
     * 成员等级
     */
    private String level;
    /**
     * 地区
     */
    private String area;
    /**
     * 专属头衔
     */
    private String title;
}
