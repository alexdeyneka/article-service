package com.article.service.jwt_config;

import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;

}
