package com.article.service.utils;

import com.article.service.entity.Article;
import com.article.service.entity.Color;
import com.article.service.entity.User;
import com.article.service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepo userRepo;

    private final Random random = new Random();

    @Autowired
    public DataLoader(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void run(ApplicationArguments args) {

        for (int a = 1; a <= 10; a++) {
            List<Article> articleList = new ArrayList<>();
            for (int i = 1; i <= random.nextInt(9) + 1; i++) {
                Article article = new Article((long) random.nextInt(), "article_content" + i + "" + a, Color.values()[random.nextInt(4)]);
                articleList.add(article);
            }
            User user = new User((long) a, "User" + a, random.nextInt(20) + 20, articleList);
            userRepo.save(user);
        }

    }

}
