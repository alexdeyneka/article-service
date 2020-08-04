package com.article.service.utils;

import com.article.service.dto.ArticleDTO;
import com.article.service.dto.mapper.ArticleMapper;
import com.article.service.dto.UserDTO;
import com.article.service.dto.mapper.UserMapper;
import com.article.service.entity.Article;
import com.article.service.entity.Color;
import com.article.service.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataGenerator {

    public List<ArticleDTO> generateArticleDTOList() {
        ArticleDTO articleDTO1 = new ArticleDTO(1L, "some_content1", Color.BLACK);
        ArticleDTO articleDTO2 = new ArticleDTO(2L, "some_content2", Color.RED);
        ArticleDTO articleDTO3 = new ArticleDTO(3L, "some_content3", Color.BLACK);
        ArticleDTO articleDTO4 = new ArticleDTO(4L, "some_content4", Color.WHITE);
        return Arrays.asList(articleDTO1, articleDTO2, articleDTO3, articleDTO4);
    }

    public List<UserDTO> generateUserDTOList() {
        UserDTO userDTO1 = new UserDTO(1L, "Bob Marley", 32, generateArticleList());
        UserDTO userDTO2 = new UserDTO(2L, "Andre Greenfield", 20, generateArticleList().subList(1, 2));
        UserDTO userDTO3 = new UserDTO(3L, "Arnold Strutsberg", 25, generateArticleList().subList(3, 4));
        UserDTO userDTO4 = new UserDTO(4L, "John Dear", 44, generateArticleList().subList(0, 1));
        return Arrays.asList(userDTO1, userDTO2, userDTO3, userDTO4);
    }

    public List<User> generateUserList() {
        List<UserDTO> tempList = generateUserDTOList();
        List<User> result = new ArrayList<>();
        tempList.forEach(n -> result.add(UserMapper.INSTANCE.userDTOToUser(n)));
        return result;
    }

    public List<Article> generateArticleList() {
        List<ArticleDTO> tempList = generateArticleDTOList();
        List<Article> result = new ArrayList<>();
        tempList.forEach(n -> result.add(ArticleMapper.INSTANCE.articleDTOToArticle(n)));
        return result;
    }
}
