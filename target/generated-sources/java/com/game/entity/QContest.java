package com.game.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContest is a Querydsl query type for Contest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContest extends EntityPathBase<Contest> {

    private static final long serialVersionUID = -341863560L;

    public static final QContest contest = new QContest("contest");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DatePath<java.time.LocalDate> contestDate = createDate("contestDate", java.time.LocalDate.class);

    public final StringPath contestIntro = createString("contestIntro");

    public final StringPath contestName = createString("contestName");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> upDateTime = _super.upDateTime;

    public QContest(String variable) {
        super(Contest.class, forVariable(variable));
    }

    public QContest(Path<? extends Contest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContest(PathMetadata metadata) {
        super(Contest.class, metadata);
    }

}

