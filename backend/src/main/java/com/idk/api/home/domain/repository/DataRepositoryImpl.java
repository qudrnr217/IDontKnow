package com.idk.api.home.domain.repository;

import com.idk.api.home.domain.entity.Data;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static com.idk.api.home.domain.entity.QData.data;

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
                        data.id.districtId.eq(districtId),
                        data.id.time.eq(LocalTime.of(LocalTime.now(ZoneId.of("Asia/Seoul")).getHour(), 0, 0))
                )
                .orderBy(data.orderQuantity.desc())
                .limit(2)
                .fetch();
    }
}
