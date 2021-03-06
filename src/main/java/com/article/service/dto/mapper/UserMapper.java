package com.article.service.dto.mapper;

import com.article.service.dto.UserDTO;
import com.article.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDTOToUser(UserDTO userDTO);

    UserDTO userToUserDTO(User user);
}
