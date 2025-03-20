package com.gsm._8th.class4.backend.task28.domain.orderItem.entity;

import com.gsm._8th.class4.backend.task28.domain.item.entity.ItemJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor
public class OrderItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderJpaEntity order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemJpaEntity item;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}