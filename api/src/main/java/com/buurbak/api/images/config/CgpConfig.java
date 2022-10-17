package com.buurbak.api.images.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "gcp")
@Configuration("cgpConfig")
@Getter
@Setter
public class CgpConfig {
    private String authFile;
    private String projectId;
    private String bucketId;
    private String dirName;
}
