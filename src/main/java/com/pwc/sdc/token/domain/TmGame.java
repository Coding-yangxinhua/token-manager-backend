package com.pwc.sdc.token.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Game信息表
 * @TableName TM_GAME
 */
@TableName(value ="TM_GAME")
@Data
public class TmGame implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * Game名称
     */
    private String name;

    /**
     * Game描述
     */
    private String description;

    /**
     * Game Token对应Url
     */
    private String url;

    private String errorToken;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}