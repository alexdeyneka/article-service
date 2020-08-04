package com.article.service.dto;

import com.article.service.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArticleDTO {
    private Long id;
    private String text;
    private Color color;
}
