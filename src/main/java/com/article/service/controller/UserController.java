package com.article.service.controller;

import com.article.service.dto.UserDTO;
import com.article.service.dto.UserMapper;
import com.article.service.entity.Color;
import com.article.service.entity.User;
import com.article.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/age")
    public ResponseEntity<List<UserDTO>> findUserByAge(@RequestParam int age) {
        List<UserDTO> userDTOList = userService.findByAge(age);
        if (userDTOList.isEmpty()) {
            log.warn("No users found with age greater than " + age);
        }
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping("/color")
    public ResponseEntity<List<UserDTO>> findUserByArticle(@RequestParam Color color) {
        List<UserDTO> userDTOList = userService.findByArticleColor(color);
        if (userDTOList.isEmpty()) {
            log.warn("No users found with article color " + color.toString());
        }
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping("/quantity")
    public ResponseEntity<List<UserDTO>> findUserWithMoreThanThreeArticles() {
        List<UserDTO> userDTOList = userService.findByArticlesQuantity();
        if (userDTOList.isEmpty()) {
            log.warn("No users found with more than three articles");
        }
        return ResponseEntity.ok().body(userDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestParam User user) {
        UserDTO userDTO = userService.saveUser(UserMapper.INSTANCE.userToUserDTO(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
}
