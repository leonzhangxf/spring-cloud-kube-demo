package com.leonzhangxf.cloud.kube.gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@SpringCloudApplication
public class GatewayDistApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayDistApplication.class).run(args);
    }
}
