package org.helmo.HolyD.repository.DTO.enums;

public enum ProviderType {
    GOOGLE("GOOGLE");

    private final String value;

    ProviderType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
