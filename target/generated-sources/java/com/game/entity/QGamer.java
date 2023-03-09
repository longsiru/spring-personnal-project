package com.game.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGamer is a Querydsl query type for Gamer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGamer extends EntityPathBase<Gamer> {

    private static final long serialVersionUID = 1129175588L;

    public static final QGamer gamer = new QGamer("gamer");

    public final StringPath gamerAge = createString("gamerAge");

    public final StringPath gamerEmail = createString("gamerEmail");

    public final StringPath gamerGender = createString("gamerGender");

    public final StringPath gamerIntro = createString("gamerIntro");

    public final StringPath gamerName = createString("gamerName");

    public final StringPath gamerRoute = createString("gamerRoute");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QGamer(String variable) {
        super(Gamer.class, forVariable(variable));
    }

    public QGamer(Path<? extends Gamer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGamer(PathMetadata metadata) {
        super(Gamer.class, metadata);
    }

}

