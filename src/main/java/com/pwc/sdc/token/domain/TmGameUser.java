package com.pwc.sdc.token.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 游戏用户表
 * @TableName TM_GAME_USER
 */
@TableName(value ="TM_GAME_USER")
@Data
public class TmGameUser implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 游戏ID
     */
    private Long gameId;

    /**
     * 微信名称
     */
    private String wechatName;

    /**
     * 用户游戏名称
     */
    private String userName;

    /**
     * 游戏用户token
     */
    private String token;

    /**
     * 是否启用 0 - 禁用 1 - 启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}