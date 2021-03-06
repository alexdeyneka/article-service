package com.article.service.service;

import com.article.service.dto.UserDTO;
import com.article.service.dto.mapper.UserMapper;
import com.article.service.entity.Article;
import com.article.service.entity.Color;
import com.article.service.entity.User;
import com.article.service.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public List<UserDTO> findByAge(int age) {
        List<User> entityList = userRepo.findAll().stream()
                .filter(element -> element.getAge() > age)
                .collect(Collectors.toList());
        List<UserDTO> entityDTOList = new ArrayList<>();
        entityList.forEach(element -> entityDTOList.add(UserMapper.INSTANCE.userToUserDTO(element)));
        return entityDTOList;
    }

    public List<UserDTO> findByArticleColor(Color color) {
        List<User> entityList = userRepo.findAll();
        List<UserDTO> entityDTOList = new ArrayList<>();
        for (User user : entityList) {
            for (Article article : user.getArticles()) {
                if (article.getColor().equals(color)) {
                    entityDTOList.add(UserMapper.INSTANCE.userToUserDTO(user));
                    break;
                }
            }
        }
        return entityDTOList;
    }

    public List<UserDTO> findByArticlesQuantity() {
        List<User> entityList = userRepo.findAll().stream()
                .filter(element -> element.getArticles().size() > 3)
                .filter(distinctByKey(User::getName))
                .collect(Collectors.toList());
        List<UserDTO> entityDTOList = new ArrayList<>();
        entityList.forEach(element -> entityDTOList.add(UserMapper.INSTANCE.userToUserDTO(element)));
        return entityDTOList;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepo.save(UserMapper.INSTANCE.userDTOToUser(userDTO));
        return UserMapper.INSTANCE.userToUserDTO(savedUser);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
