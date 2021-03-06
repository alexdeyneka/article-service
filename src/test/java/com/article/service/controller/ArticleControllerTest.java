package com.article.service.controller;

import com.article.service.dto.ArticleDTO;
import com.article.service.jwt_config.JwtAuthenticationEntryPoint;
import com.article.service.jwt_config.JwtTokenUtil;
import com.article.service.service.ArticleService;
import com.article.service.service.JwtUserDetailsService;
import com.article.service.utils.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ArticleControllerTest {

    @MockBean
    private ArticleService articleService;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private MockMvc mockMvc;

    private final List<ArticleDTO> articleDTOList = new TestDataGenerator().generateArticleDTOList();

    @Test
    void saveArticle() throws Exception {
        given(articleService.saveArticle(any(ArticleDTO.class))).willReturn(articleDTOList.get(0));
        this.mockMvc.perform(post("/article/save")
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU5NjcxOTUxOSwiaWF0IjoxNTk2NzAxNTE5fQ.ICQuGmadQC9kpD8gFYjRYKsPYRG_FHpPsnH_9-gRidi8iigZ9GZnSnHgI71tV7hUjCrlgcfeWb95DXdTVnkGNg")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"some_content\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(articleDTOList.get(0).getId()));
        verify(articleService, times(1)).saveArticle(any(ArticleDTO.class));
        verify(articleService, only()).saveArticle(any(ArticleDTO.class));
    }
}