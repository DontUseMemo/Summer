package com.example.oppu.constant;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
