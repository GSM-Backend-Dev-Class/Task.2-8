package com.gsm._8th.class4.backend.task28.domain.order.repository;

import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {

    /**
     * 주문 검색 메서드 (JPQL 기반 - QueryDSL 마이그레이션 대상)
     *
     * <p>
     * 이 메서드는 사용자 ID, 가격 범위, 주소, 상품명 등 다양한 조건에 따라 주문을 조회하는 예시입니다.<br>
     * 내부적으로 주문 → 주문 아이템 → 상품(Item)까지 JOIN하여 필터링하며, 결과는 페이징(Pageable) 처리됩니다.
     * </p>
     *
     * <p>
     * ⚠️ 본 메서드는 의도적으로 <b>N+1 문제</b>가 발생하도록 설계되었습니다.<br>
     * 즉, 연관 엔티티인 <code>orderItems</code> 및 <code>item</code>은 Lazy 로딩되며,
     * 실제 조회 시 각 주문마다 추가 쿼리가 발생합니다.
     * </p>
     *
     * <p>
     * 이 메서드는 <b>QueryDSL 기반의 동적 쿼리로 마이그레이션</b>해야 하며,
     * 필요 시 <code>fetch join</code> 또는 <code>@EntityGraph</code> 등을 통해 N+1 문제를 해결하도록 유도합니다.
     * </p>
     *
     * @param userId   검색할 사용자 ID
     * @param minPrice 최소 주문 가격
     * @param maxPrice 최대 주문 가격
     * @param address  포함되어야 할 배송지 문자열 (LIKE 검색)
     * @param itemName 포함되어야 할 상품명 문자열 (LIKE 검색)
     * @param pageable 페이징 및 정렬 정보를 담는 Pageable 객체
     * @return 조건에 맞는 주문 목록을 포함한 Page 객체
     */
    @Query(
            "SELECT DISTINCT o FROM OrderJpaEntity o " +
                    "JOIN o.orderItems oi " +
                    "JOIN oi.item i " +
                    "WHERE o.user.id = :user_id " +
                    "AND o.price >= :min_price " +
                    "AND o.price <= :max_price " +
                    "AND o.address LIKE %:address% " +
                    "AND i.name LIKE %:item_name%"
    )
    Page<OrderJpaEntity> searchOrders(
            @Param("user_id") Long userId,
            @Param("min_price") int minPrice,
            @Param("max_price") int maxPrice,
            @Param("address") String address,
            @Param("item_name") String itemName,
            Pageable pageable
    );
}