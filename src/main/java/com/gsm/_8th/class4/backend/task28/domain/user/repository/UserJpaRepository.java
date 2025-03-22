package com.gsm._8th.class4.backend.task28.domain.user.repository;

import com.gsm._8th.class4.backend.task28.domain.user.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
}