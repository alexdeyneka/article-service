package com.article.service.service;

import com.article.service.dto.ArticleDTO;
import com.article.service.dto.mapper.ArticleMapper;
import com.article.service.entity.Article;
import com.article.service.repository.ArticleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepo articleRepo;

    // saves the article to db
    public ArticleDTO saveArticle(ArticleDTO articleDTO) {
        Article savedArticle = articleRepo.save(ArticleMapper.INSTANCE.articleDTOToArticle(articleDTO));
        return ArticleMapper.INSTANCE.articleToArticleDTO(savedArticle);
    }
}
