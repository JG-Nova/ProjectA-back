package org.jgprojects.a.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@ConfigurationProperties
@Getter
@Setter
@ToString
public class Config {
    private String jwtSecret;
    private String jwtIssuer;
}
