package com.article.service.service;

import com.article.service.dto.ArticleDTO;
import com.article.service.dto.ArticleMapper;
import com.article.service.dto.UserDTO;
import com.article.service.dto.UserMapper;
import com.article.service.entity.Article;
import com.article.service.entity.User;
import com.article.service.repository.ArticleRepo;
import com.article.service.repository.UserRepo;
import com.article.service.utils.TestDataGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepo articleRepo;

    @InjectMocks
    private ArticleService articleService;

    private final List<Article> articleList = new TestDataGenerator().generateArticleList();

    @Test
    void saveArticle() {
        when(articleRepo.save(any(Article.class))).thenReturn(articleList.get(0));
        ArticleDTO actualArticle = articleService.saveArticle(new ArticleDTO());
        assertEquals(ArticleMapper.INSTANCE.articleToArticleDTO(articleList.get(0)), actualArticle);
        verify(articleRepo, times(1)).save(any(Article.class));
        verify(articleRepo, only()).save(any(Article.class));
    }
}