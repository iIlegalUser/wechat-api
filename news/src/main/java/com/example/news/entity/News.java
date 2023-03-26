package com.example.news.entity;

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
 * 消息发布
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
@Getter
@Setter
@TableName("news")
public class News implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 消息内容
     */
    @TableField("content")
    private String content;

    /**
     * 转载来源
     */
    @TableField("source")
    private String source;

    /**
     * 源地址
     */
    @TableField("url")
    private String url;

    /**
     * 消息发布时间
     */
    @TableField("pub_at")
    private Date pubAt;


}
