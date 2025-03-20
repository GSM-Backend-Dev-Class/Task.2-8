package com.gsm._8th.class4.backend.task28.domain.user.entity;

import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_age", nullable = false)
    private int age;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_address", nullable = false)
    private String address;

    @Column(name = "user_phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderJpaEntity> orders = new ArrayList<>();
}