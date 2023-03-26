package com.example.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一标识
     */
    @TableField("openid")
    private String openId;

    /**
     * 会话密钥
     */
    @TableField("session_key")
    private String sessionKey;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 管理员
     */
    @TableField("admin")
    private Boolean isAdmin;

    /**
     * 头像URL
     */
    @TableField("icon")
    private String icon;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;

    /**
     * 最后登录时间
     */
    @TableField("last_login")
    private Date lastLogin;

}
