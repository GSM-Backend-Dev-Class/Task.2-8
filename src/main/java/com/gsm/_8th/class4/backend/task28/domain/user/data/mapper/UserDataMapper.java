package com.gsm._8th.class4.backend.task28.domain.user.data.mapper;

import com.gsm._8th.class4.backend.task28.domain.user.data.UserDto;
import com.gsm._8th.class4.backend.task28.domain.user.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    public UserDto toUserDto(UserJpaEntity user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getEmail(),
                user.getAddress(),
                user.getPhoneNumber()
        );
    }
}
