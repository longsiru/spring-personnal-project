package com.game.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.game.dto.QTeamDetailDto is a Querydsl Projection type for TeamDetailDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTeamDetailDto extends ConstructorExpression<TeamDetailDto> {

    private static final long serialVersionUID = -1510787053L;

    public QTeamDetailDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> teamName, com.querydsl.core.types.Expression<String> teamEmail, com.querydsl.core.types.Expression<String> teamRank, com.querydsl.core.types.Expression<String> teamWin, com.querydsl.core.types.Expression<String> teamLose, com.querydsl.core.types.Expression<String> teamintro, com.querydsl.core.types.Expression<String> teamImageUrl) {
        super(TeamDetailDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}, id, teamName, teamEmail, teamRank, teamWin, teamLose, teamintro, teamImageUrl);
    }

}

