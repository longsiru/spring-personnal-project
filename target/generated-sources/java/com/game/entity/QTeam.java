package com.game.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = -1348657543L;

    public static final QTeam team = new QTeam("team");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath teamEmail = createString("teamEmail");

    public final StringPath teamIntro = createString("teamIntro");

    public final StringPath teamLose = createString("teamLose");

    public final StringPath teamName = createString("teamName");

    public final StringPath teamRank = createString("teamRank");

    public final StringPath teamWin = createString("teamWin");

    public QTeam(String variable) {
        super(Team.class, forVariable(variable));
    }

    public QTeam(Path<? extends Team> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeam(PathMetadata metadata) {
        super(Team.class, metadata);
    }

}

