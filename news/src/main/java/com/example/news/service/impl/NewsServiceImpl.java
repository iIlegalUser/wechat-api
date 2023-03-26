package com.example.news.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.news.entity.News;
import com.example.news.mapper.NewsMapper;
import com.example.news.service.INewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 消息发布 服务实现类
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    private final NewsMapper newsMapper;

    //查询记录数
    @Override
    public Long queryCount() {
        return newsMapper.selectCount(null);
    }

    //查询所有
    @Override
    public List<News> queryAll() {
        return newsMapper.selectList(null);
    }

    //分页查询
    @Override
    public List<News> queryByPage(IPage<News> page) {
        return newsMapper.selectPage(page, null).getRecords();
    }

    //根据id查询
    @Override
    public News queryById(Integer id) {
        return newsMapper.selectById(id);
    }

    //新建
    @Override
    public Integer create(News news) {
        return newsMapper.insert(news);
    }

    //删除
    @Override
    public Integer delete(Integer id) {
        return newsMapper.deleteById(id);
    }

    //更新
    @Override
    public Integer update(News news) {
        return newsMapper.updateById(news);
    }

}
