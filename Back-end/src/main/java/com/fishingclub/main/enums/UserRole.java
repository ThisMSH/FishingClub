package com.fishingclub.main.enums;

public enum UserRole {
    MEMBER("Member"),
    JURY("Jury"),
    MANAGER("Manager");

    UserRole(String value) {
        this.value = value;
    }

    private final String value;

    public String value() {
        return this.value;
    }

    public static UserRole fromValue(String value) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.value.equals(value)) {
                return userRole;
            }
        }

        throw new IllegalArgumentException("No enum constant with value " + value + ".");
    }
}
