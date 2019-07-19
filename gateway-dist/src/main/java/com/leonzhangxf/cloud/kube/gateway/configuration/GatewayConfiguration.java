package com.leonzhangxf.cloud.kube.gateway.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author leonzhangxf
 * @date 20190719
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "gateway.config")
public class GatewayConfiguration {

    private String message;
}
