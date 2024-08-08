package org.choongang.farm.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFarmTourplace is a Querydsl query type for FarmTourplace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFarmTourplace extends EntityPathBase<FarmTourplace> {

    private static final long serialVersionUID = 271741032L;

    public static final QFarmTourplace farmTourplace = new QFarmTourplace("farmTourplace");

    public final org.choongang.global.entities.QBaseEntity _super = new org.choongang.global.entities.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath description = createString("description");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath photoUrl = createString("photoUrl");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath tel = createString("tel");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> tourDays = createNumber("tourDays", Integer.class);

    public QFarmTourplace(String variable) {
        super(FarmTourplace.class, forVariable(variable));
    }

    public QFarmTourplace(Path<? extends FarmTourplace> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFarmTourplace(PathMetadata metadata) {
        super(FarmTourplace.class, metadata);
    }

}

