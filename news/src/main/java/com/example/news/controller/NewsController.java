package com.example.news.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlInjectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.exception.ArgumentException;
import com.example.common.exception.NotFoundException;
import com.example.news.entity.News;
import com.example.news.entity.vo.PageVO;
import com.example.news.service.INewsService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 消息发布 前端控制器
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final INewsService newsService;

    /**
     * 创建 news
     * @param news news
     * @return success/false
     */
    @PostMapping("/create")
    public ResponseEntity<String> create(News news, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date pubDate) {
        if (Strings.isNullOrEmpty(news.getTitle())) {
            throw new ArgumentException("标题不能为空");
        }
        if (Strings.isNullOrEmpty(news.getContent())) {
            throw new ArgumentException("内容不能为空");
        }
        if (pubDate == null) {
            throw new ArgumentException("发布时间不能为空");
        }
        news.setPubAt(pubDate);
        if (newsService.create(news) <= 0) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok("success");
    }

    /**
     * 删除 news
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (newsService.delete(id) <= 0) {
            throw new RuntimeException("删除失败");
        }
        return ResponseEntity.ok("success");
    }

    /**
     * 分页查询
     * @param curPage 当前页
     * @param limit 每页条数
     * @param item 排序字段
     * @param order 排序方式
     * @return 分页结果
     */
    @GetMapping("/page")
    public ResponseEntity<PageVO> queryByPage(@RequestParam(name = "p", required = false) Integer curPage,
                                              @RequestParam(name = "l", required = false) Integer limit,
                                              @RequestParam(name = "i", required = false) String item,
                                              @RequestParam(name = "o", required = false) String order) {
        Page<News> page = new Page<>();
        if (curPage != null && curPage > 0) {
            page.setCurrent(curPage);
        }
        if (limit != null && limit > 0) {
            page.setSize(limit);
        }
        boolean asc = order.equals("asc");
        if (!Strings.isNullOrEmpty(item)) {
            if (SqlInjectionUtils.check(order))
                throw new ArgumentException("不存在的排序字段");
            page.addOrder(new OrderItem(item, asc));
        } else {
            page.addOrder(new OrderItem("pub_at", asc));
        }
        List<News> news = newsService.queryByPage(page);
        long count = newsService.queryCount();
        PageVO pageVO = new PageVO(news, count);
        if (news == null) {
            throw new NotFoundException("没有找到记录");
        }
        return ResponseEntity.ok(pageVO);
    }

    /**
     * 查询所有
     * @return 所有结果
     */
    @GetMapping("/list")
    public ResponseEntity<List<News>> queryAll() {
        List<News> news = newsService.queryAll();
        if (news == null) {
            throw new NotFoundException("没有找到记录");
        }
        return ResponseEntity.ok(news);
    }
}
