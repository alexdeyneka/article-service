package com.article.service.repository;

import com.article.service.entity.Article;
import com.article.service.utils.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class ArticleRepoTest {

    @Autowired
    private ArticleRepo articleRepo;

    private static final List<Article> articlesList = new TestDataGenerator().generateArticleList();

    @BeforeEach
    void setUp() {
        populateDB();
    }

    @Test
    void testSave() {
        articleRepo.save(new Article());
        List<Article> testList = articleRepo.findAll();
        assertEquals(testList.size(), articlesList.size() + 1);
    }

    public void populateDB() {
        articlesList.forEach(n -> articleRepo.save(n));
    }
}