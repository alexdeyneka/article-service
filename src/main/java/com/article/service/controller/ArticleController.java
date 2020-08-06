package com.article.service.controller;

import com.article.service.dto.ArticleDTO;
import com.article.service.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/save")
    public ResponseEntity<ArticleDTO> saveArticle(@RequestBody ArticleDTO articleDTO) {
        log.debug("About to save " + articleDTO);
        ArticleDTO savedArticleDTO = articleService.saveArticle(articleDTO);
        log.debug(articleDTO + " saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticleDTO);
    }
}
