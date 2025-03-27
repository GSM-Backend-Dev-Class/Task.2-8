package com.gsm._8th.class4.backend.task28.domain.order.repository.custom;

import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.order.entity.QOrderJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.orderItem.entity.QOrderItemJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.item.entity.QItemJpaEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final JPAQueryFactory queryFactory;

    public OrderCustomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<OrderJpaEntity> searchOrders(Long userId, Integer minPrice, Integer maxPrice, String address, String itemName, Pageable pageable) {
        QOrderJpaEntity order = QOrderJpaEntity.orderJpaEntity;
        QOrderItemJpaEntity orderItem = QOrderItemJpaEntity.orderItemJpaEntity;
        QItemJpaEntity item = QItemJpaEntity.itemJpaEntity;
        BooleanBuilder builder = new BooleanBuilder();

        if (userId != null) {
            builder.and(order.user.id.eq(userId));
        }
        if (minPrice != null) {
            builder.and(order.price.goe(minPrice));
        }
        if (maxPrice != null) {
            builder.and(order.price.loe(maxPrice));
        }
        if (address != null) {
            builder.and(order.address.contains(address));
        }
        if (itemName != null) {
            builder.and(orderItem.item.name.contains(itemName));
        }

        List<OrderJpaEntity> results = queryFactory
                .selectFrom(order)
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .leftJoin(orderItem.item, item).fetchJoin()
                .where(builder)
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(order)
                .leftJoin(order.orderItems, orderItem)
                .leftJoin(orderItem.item, item)
                .where(builder)
                .distinct()
                .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}