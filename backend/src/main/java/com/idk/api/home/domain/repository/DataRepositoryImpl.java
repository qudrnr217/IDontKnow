package com.idk.api.home.domain.repository;

import com.idk.api.home.domain.entity.Data;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.idk.api.home.domain.entity.QData.data;
import javax.persistence.EntityManager;
import java.util.List;

public class DataRepositoryImpl implements DataRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public DataRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Data> searchMenusAtThisTimeInAddress(Integer districtId) {
        return queryFactory
                .selectFrom(data)
                .where(
                        data.districtCode.id.eq(districtId),
                        Expressions.numberTemplate(Integer.class, "HOUR(CURRENT_TIME)").eq(data.id.time.hour())
                )
                .orderBy(data.orderQuantity.desc())
                .limit(2)
                .fetch();
    }
}
