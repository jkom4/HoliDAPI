package org.helmo.HolyD.repository.DTO.enums;

public enum RoleType {
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    RoleType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public int toInt(){
        return this.ordinal() + 1;
    }
}
