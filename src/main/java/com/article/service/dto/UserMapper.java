package com.article.service.dto;

import com.article.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User orderDTOToOrder(UserDTO orderDTO);

    UserDTO orderToOrderDTO(User order);
}
