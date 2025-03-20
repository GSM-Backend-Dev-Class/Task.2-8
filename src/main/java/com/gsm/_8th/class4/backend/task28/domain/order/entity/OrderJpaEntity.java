package com.gsm._8th.class4.backend.task28.domain.order.entity;

import com.gsm._8th.class4.backend.task28.domain.item.entity.ItemJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.orderItem.entity.OrderItemJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.user.entity.UserJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_uuid", columnDefinition = "BINARY(16)", nullable = false, unique = true)
    @UuidGenerator
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @Column(name = "order_price", nullable = false)
    private int price;

    @Column(name = "order_address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJpaEntity> orderItems = new ArrayList<>();
}