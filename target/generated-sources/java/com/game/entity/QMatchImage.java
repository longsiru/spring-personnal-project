package com.game.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchImage is a Querydsl query type for MatchImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchImage extends EntityPathBase<MatchImage> {

    private static final long serialVersionUID = 485788082L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchImage matchImage = new QMatchImage("matchImage");

    public final QContest contest;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath matchImageName = createString("matchImageName");

    public final StringPath matchImageUrl = createString("matchImageUrl");

    public final StringPath matchOriImgName = createString("matchOriImgName");

    public final StringPath matchRepimgYn = createString("matchRepimgYn");

    public QMatchImage(String variable) {
        this(MatchImage.class, forVariable(variable), INITS);
    }

    public QMatchImage(Path<? extends MatchImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchImage(PathMetadata metadata, PathInits inits) {
        this(MatchImage.class, metadata, inits);
    }

    public QMatchImage(Class<? extends MatchImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contest = inits.isInitialized("contest") ? new QContest(forProperty("contest")) : null;
    }

}

