package com.game.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamImage is a Querydsl query type for TeamImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamImage extends EntityPathBase<TeamImage> {

    private static final long serialVersionUID = -1902657950L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeamImage teamImage = new QTeamImage("teamImage");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final QTeam team;

    public final StringPath teamImageName = createString("teamImageName");

    public final StringPath teamImageUrl = createString("teamImageUrl");

    public final StringPath teamOriImgName = createString("teamOriImgName");

    public final StringPath teamRepimgYn = createString("teamRepimgYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> upDateTime = _super.upDateTime;

    public QTeamImage(String variable) {
        this(TeamImage.class, forVariable(variable), INITS);
    }

    public QTeamImage(Path<? extends TeamImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeamImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeamImage(PathMetadata metadata, PathInits inits) {
        this(TeamImage.class, metadata, inits);
    }

    public QTeamImage(Class<? extends TeamImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

