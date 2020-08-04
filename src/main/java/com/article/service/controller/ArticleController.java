package com.article.service.controller;

import com.article.service.dto.ArticleDTO;
import com.article.service.dto.ArticleMapper;
import com.article.service.entity.Article;
import com.article.service.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/save")
    public ResponseEntity<ArticleDTO> saveUser(@RequestParam Article article) {
        ArticleDTO articleDTO = articleService.saveArticle(ArticleMapper.INSTANCE.articleToArticleDTO(article));
        return ResponseEntity.status(HttpStatus.CREATED).body(articleDTO);
    }
}
