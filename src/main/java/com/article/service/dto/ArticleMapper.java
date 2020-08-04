package com.article.service.dto;

import com.article.service.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article articleDTOToArticle(ArticleDTO articleDTO);

    ArticleDTO articleToArticleDTO(Article article);
}
