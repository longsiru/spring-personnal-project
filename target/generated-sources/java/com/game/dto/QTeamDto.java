package com.game.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.game.dto.QTeamDto is a Querydsl Projection type for TeamDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTeamDto extends ConstructorExpression<TeamDto> {

    private static final long serialVersionUID = 1109839332L;

    public QTeamDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> teamName, com.querydsl.core.types.Expression<String> teamImageUrl) {
        super(TeamDto.class, new Class<?>[]{long.class, String.class, String.class}, id, teamName, teamImageUrl);
    }

}

