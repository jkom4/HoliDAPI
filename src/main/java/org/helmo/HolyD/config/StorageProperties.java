package org.helmo.HolyD.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    private final String location = "/documents/vacances/";

    public String getLocationVacanceDocuments(Long idVacance) {
        return location + idVacance + "/";
    }

    public String getLocationActivityDocuments(Long idVacance, Long idActivity) {
        return this.getLocationVacanceDocuments(idVacance) + "/activities/" + idActivity + "/";
    }



}
