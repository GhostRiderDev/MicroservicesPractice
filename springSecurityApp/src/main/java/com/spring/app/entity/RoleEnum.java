package com.spring.app.entity;

public enum RoleEnum {
    ADMIN("ADMIN"),
    USER("USER"),
    INVITED("INVITED"),
    DEVELOPER("DEVELOPER");
    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
