package com.article.service.controller;

import com.article.service.dto.UserDTO;
import com.article.service.entity.Color;
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

    private static final String COMPLETED = "Search completed";

    @GetMapping("/age")
    public ResponseEntity<List<UserDTO>> findUserByAge(@RequestParam int age) {
        log.debug("About to find the user by age " + age);
        List<UserDTO> userDTOList = userService.findByAge(age);
        log.debug(COMPLETED);
        if (userDTOList.isEmpty()) {
            log.warn("No users found with age greater than " + age);
        }
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping("/color")
    public ResponseEntity<List<UserDTO>> findUserByArticle(@RequestParam Color color) {
        log.debug("About to find the users by article color " + color);
        List<UserDTO> userDTOList = userService.findByArticleColor(color);
        log.debug(COMPLETED);
        if (userDTOList.isEmpty()) {
            log.warn("No users found with article color " + color.toString());
        }
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping("/quantity")
    public ResponseEntity<List<UserDTO>> findUserWithMoreThanThreeArticles() {
        log.debug("About to find the users by article quantity");
        List<UserDTO> userDTOList = userService.findByArticlesQuantity();
        log.debug(COMPLETED);
        if (userDTOList.isEmpty()) {
            log.warn("No users found with more than three articles");
        }
        return ResponseEntity.ok().body(userDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        log.debug("About to save the user " + userDTO);
        UserDTO savedUser = userService.saveUser(userDTO);
        log.debug(COMPLETED);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
