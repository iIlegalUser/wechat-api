package com.example.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.news.entity.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息发布 Mapper 接口
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}
