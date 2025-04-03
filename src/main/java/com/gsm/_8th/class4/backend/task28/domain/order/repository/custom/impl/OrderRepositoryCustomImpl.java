package com.gsm._8th.class4.backend.task28.domain.order.repository.custom.impl;

import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.order.repository.custom.OrderRepositoryCustom;
import com.gsm._8th.class4.backend.task28.domain.order.entity.QOrderJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.item.entity.QItemJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.orderItem.entity.QOrderItemJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.user.entity.QUserJpaEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderJpaEntity> searchOrders(Long userId, Integer minPrice, Integer maxPrice, String address, String itemName, Pageable pageable){
        QOrderJpaEntity orderJpaEntity = QOrderJpaEntity.orderJpaEntity;
        QOrderItemJpaEntity orderItemJpaEntity = QOrderItemJpaEntity.orderItemJpaEntity;
        QItemJpaEntity itemJpaEntity = QItemJpaEntity.itemJpaEntity;
        QUserJpaEntity userJpaEntity = QUserJpaEntity.userJpaEntity;

        List<OrderJpaEntity> orders = queryFactory
                .select(orderJpaEntity)
                .distinct()
                .leftJoin(orderJpaEntity.orderItems, orderItemJpaEntity).fetchJoin()
                .leftJoin(orderItemJpaEntity.item, itemJpaEntity).fetchJoin()
                .leftJoin(orderJpaEntity.user, userJpaEntity).fetchJoin()
                .where(
                        eqUserId(userId, orderJpaEntity),
                        goeMinPrice(minPrice, orderJpaEntity),
                        loeMaxPrice(maxPrice, orderJpaEntity),
                        containsAddress(address, orderJpaEntity),
                        containsItemName(itemName, itemJpaEntity)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(orderJpaEntity.count())
                .from(orderJpaEntity)
                .where(
                        eqUserId(userId, orderJpaEntity),
                        goeMinPrice(minPrice, orderJpaEntity),
                        loeMaxPrice(maxPrice, orderJpaEntity),
                        containsAddress(address, orderJpaEntity),
                        containsItemName(itemName, itemJpaEntity)
                )
                .fetchOne();

        long total = (totalCount != null) ? totalCount : 0L;
        return new PageImpl<>(orders,pageable,total);
    }

    private BooleanExpression eqUserId(Long userId, QOrderJpaEntity orderJpaEntity) {
        if (userId != null) {
            return orderJpaEntity.user.id.eq(userId);
        } else {
            return Expressions.asBoolean(true).isTrue();
        }
    }

    private BooleanExpression goeMinPrice(Integer minPrice, QOrderJpaEntity orderJpaEntity) {
        if (minPrice != null){
            return orderJpaEntity.price.goe(minPrice);
        }
        else{
            return Expressions.asBoolean(true).isTrue();
        }
    }

    private BooleanExpression loeMaxPrice(Integer maxPrice, QOrderJpaEntity orderJpaEntity) {
        if (maxPrice != null){
            return orderJpaEntity.price.loe(maxPrice);
        }
        else{
            return Expressions.asBoolean(true).isTrue();
        }
    }

    private BooleanExpression containsAddress(String address, QOrderJpaEntity orderJpaEntity) {
        if (address != null){
            return orderJpaEntity.address.contains(address);
        }
        else{
            return Expressions.asBoolean(true).isTrue();
        }
    }

    private BooleanExpression containsItemName(String itemName, QItemJpaEntity itemJpaEntity) {
        if (itemName != null){
            return itemJpaEntity.name.contains(itemName);
        }
        else{
            return Expressions.asBoolean(true).isTrue();
        }
    }
}
