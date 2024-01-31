package org.jgprojects.a.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location;

    public StorageProperties() {
        this.location = "upload-dir";
    }

    
}
