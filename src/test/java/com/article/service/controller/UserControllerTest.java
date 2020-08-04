package com.article.service.controller;

import com.article.service.dto.UserDTO;
import com.article.service.entity.Color;
import com.article.service.service.UserService;
import com.article.service.utils.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private final List<UserDTO> userDTOList = new TestDataGenerator().generateUserDTOList();

    @Test
    void findUserByAge() throws Exception {
        given(userService.findByAge(anyInt())).willReturn(userDTOList);
        this.mockMvc.perform(get("/user/age")
                .param("age", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].age").value(userDTOList.get(0).getAge()))
                .andExpect(jsonPath("$.[1].age").value(userDTOList.get(1).getAge()))
                .andExpect(jsonPath("$.[2].age").value(userDTOList.get(2).getAge()));
        verify(userService, times(1)).findByAge(anyInt());
        verify(userService, only()).findByAge(anyInt());
    }

    @Test
    void findUserByArticle() throws Exception {
        given(userService.findByArticleColor(any(Color.class))).willReturn(userDTOList);
        this.mockMvc.perform(get("/user/color")
                .param("color", "RED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value(userDTOList.get(0).getName()))
                .andExpect(jsonPath("$.[1].name").value(userDTOList.get(1).getName()))
                .andExpect(jsonPath("$.[2].name").value(userDTOList.get(2).getName()));
        verify(userService, times(1)).findByArticleColor(any(Color.class));
        verify(userService, only()).findByArticleColor(any(Color.class));
    }

    @Test
    void findUserWithMoreThanThreeArticles() throws Exception {
        given(userService.findByArticlesQuantity()).willReturn(userDTOList);
        this.mockMvc.perform(get("/user/quantity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].articles.[0].color")
                        .value(userDTOList.get(0).getArticles().get(0).getColor().toString()))
                .andExpect(jsonPath("$.[1].articles.[0].color")
                        .value(userDTOList.get(1).getArticles().get(0).getColor().toString()))
                .andExpect(jsonPath("$.[2].articles.[0].color")
                        .value(userDTOList.get(2).getArticles().get(0).getColor().toString()));
        verify(userService, times(1)).findByArticlesQuantity();
        verify(userService, only()).findByArticlesQuantity();
    }

    @Test
    void saveUser() throws Exception {
        given(userService.saveUser(any(UserDTO.class))).willReturn(userDTOList.get(0));
        this.mockMvc.perform(post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Bob\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userDTOList.get(0).getId()));
        verify(userService, times(1)).saveUser(any(UserDTO.class));
        verify(userService, only()).saveUser(any(UserDTO.class));
    }
}