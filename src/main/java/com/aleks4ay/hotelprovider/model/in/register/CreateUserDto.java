package com.aleks4ay.hotelprovider.model.in.register;

public record CreateUserDto(String username, String email, String password, String firstName, String lastName, boolean enabled) {
}
