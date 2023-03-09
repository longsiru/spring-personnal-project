package com.game.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.game.dto.QGamerDetailDto is a Querydsl Projection type for GamerDetailDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGamerDetailDto extends ConstructorExpression<GamerDetailDto> {

    private static final long serialVersionUID = 752342796L;

    public QGamerDetailDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> gamerName, com.querydsl.core.types.Expression<String> gamerEmail, com.querydsl.core.types.Expression<String> gamerAge, com.querydsl.core.types.Expression<String> gamerGender, com.querydsl.core.types.Expression<String> teamIntro, com.querydsl.core.types.Expression<String> gamerRoute, com.querydsl.core.types.Expression<String> gamerImageUrl) {
        super(GamerDetailDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}, id, gamerName, gamerEmail, gamerAge, gamerGender, teamIntro, gamerRoute, gamerImageUrl);
    }

}

