package com.game.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGamerImage is a Querydsl query type for GamerImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGamerImage extends EntityPathBase<GamerImage> {

    private static final long serialVersionUID = 116430231L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGamerImage gamerImage = new QGamerImage("gamerImage");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final QGamer gamer;

    public final StringPath gamerImageName = createString("gamerImageName");

    public final StringPath gamerImageUrl = createString("gamerImageUrl");

    public final StringPath gamerOriImgName = createString("gamerOriImgName");

    public final StringPath gamerRepimgYn = createString("gamerRepimgYn");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> upDateTime = _super.upDateTime;

    public QGamerImage(String variable) {
        this(GamerImage.class, forVariable(variable), INITS);
    }

    public QGamerImage(Path<? extends GamerImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGamerImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGamerImage(PathMetadata metadata, PathInits inits) {
        this(GamerImage.class, metadata, inits);
    }

    public QGamerImage(Class<? extends GamerImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gamer = inits.isInitialized("gamer") ? new QGamer(forProperty("gamer")) : null;
    }

}

