package com.article.service.dto;

import com.article.service.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article itemDTOToItem(ArticleDTO itemDTO);

    ArticleDTO itemToItemDTO(Article item);
}
