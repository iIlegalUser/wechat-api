package com.example.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.news.entity.News;

import java.util.List;

/**
 * <p>
 * 消息发布 服务类
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
public interface INewsService extends IService<News> {
    //查询记录数
    Long queryCount();

    //查询所有
    List<News> queryAll();

    List<News> queryByPage(IPage<News> page);

    //根据id查询
    News queryById(Integer id);

    //新建
    Integer create(News news);

    //删除
    Integer delete(Integer id);

    //更新
    Integer update(News news);

}
