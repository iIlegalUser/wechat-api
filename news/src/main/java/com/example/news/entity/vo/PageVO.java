package com.example.news.entity.vo;

import com.example.news.entity.News;

import java.util.List;

public record PageVO(List<News> newsList, Long total) {

}
