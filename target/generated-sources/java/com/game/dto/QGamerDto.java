package com.game.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.game.dto.QGamerDto is a Querydsl Projection type for GamerDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGamerDto extends ConstructorExpression<GamerDto> {

    private static final long serialVersionUID = -53978211L;

    public QGamerDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> gamerName, com.querydsl.core.types.Expression<String> gamerRoute, com.querydsl.core.types.Expression<String> gamerImageUrl) {
        super(GamerDto.class, new Class<?>[]{long.class, String.class, String.class, String.class}, id, gamerName, gamerRoute, gamerImageUrl);
    }

}

