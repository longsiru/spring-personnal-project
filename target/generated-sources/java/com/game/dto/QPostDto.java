package com.game.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.game.dto.QPostDto is a Querydsl Projection type for PostDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostDto extends ConstructorExpression<PostDto> {

    private static final long serialVersionUID = -2137051967L;

    public QPostDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> postTitle, com.querydsl.core.types.Expression<String> postText, com.querydsl.core.types.Expression<String> postImageUrl, com.querydsl.core.types.Expression<String> createdBy, com.querydsl.core.types.Expression<java.time.LocalDateTime> regTime) {
        super(PostDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, id, postTitle, postText, postImageUrl, createdBy, regTime);
    }

}

