package com.gsm._8th.class4.backend.task28.domain.user.data;

public record UserDto(
        Long id,
        String name,
        int age,
        String email,
        String address,
        String phoneNumber
) {
}