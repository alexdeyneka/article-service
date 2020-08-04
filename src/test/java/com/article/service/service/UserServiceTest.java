package com.article.service.service;

import com.article.service.dto.UserDTO;
import com.article.service.dto.UserMapper;
import com.article.service.entity.Color;
import com.article.service.entity.User;
import com.article.service.repository.UserRepo;
import com.article.service.utils.TestDataGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private final List<User> userList = new TestDataGenerator().generateUserList();

    @Test
    void findByAge() {
        when(userRepo.findAll()).thenReturn(userList);
        List<UserDTO> actualList = userService.findByAge(30);
        assertEquals(2, actualList.size());
        verify(userRepo, times(1)).findAll();
        verify(userRepo, only()).findAll();
    }

    @Test
    void findByArticleColor() {
        when(userRepo.findAll()).thenReturn(userList);
        List<UserDTO> actualList = userService.findByArticleColor(Color.RED);
        assertEquals(2, actualList.size());
        verify(userRepo, times(1)).findAll();
        verify(userRepo, only()).findAll();
    }

    @Test
    void findByArticlesQuantity() {
        List<User> testList = Arrays.asList(userList.get(0), userList.get(0), userList.get(1));
        when(userRepo.findAll()).thenReturn(testList);
        List<UserDTO> actualList = userService.findByArticlesQuantity();
        assertEquals(1, actualList.size());
        verify(userRepo, times(1)).findAll();
        verify(userRepo, only()).findAll();
    }

    @Test
    void saveUser() {
        when(userRepo.save(any(User.class))).thenReturn(userList.get(0));
        UserDTO actualUser = userService.saveUser(new UserDTO());
        assertEquals(UserMapper.INSTANCE.userToUserDTO(userList.get(0)), actualUser);
        verify(userRepo, times(1)).save(any(User.class));
        verify(userRepo, only()).save(any(User.class));
    }
}