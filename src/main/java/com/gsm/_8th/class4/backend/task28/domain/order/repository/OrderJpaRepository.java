package com.gsm._8th.class4.backend.task28.domain.order.repository;

import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {
    @EntityGraph(attributePaths = {"orderItems", "orderItems.item"})
    @Query(
            "SELECT DISTINCT o FROM OrderJpaEntity o " +
                    "JOIN o.orderItems oi " +
                    "JOIN oi.item i " +
                    "WHERE (:user_id IS NULL OR o.user.id = :user_id) " +
                    "AND (:min_price IS NULL OR o.price >= :min_price) " +
                    "AND (:max_price IS NULL OR o.price <= :max_price) " +
                    "AND (:address IS NULL OR o.address LIKE CONCAT('%', :address, '%')) " +
                    "AND (:item_name IS NULL OR i.name LIKE CONCAT('%', :item_name, '%'))"
    )
    Page<OrderJpaEntity> searchOrders(
            @Param("user_id") Long userId,
            @Param("min_price") Integer minPrice,
            @Param("max_price") Integer maxPrice,
            @Param("address") String address,
            @Param("item_name") String itemName,
            Pageable pageable
    );
}