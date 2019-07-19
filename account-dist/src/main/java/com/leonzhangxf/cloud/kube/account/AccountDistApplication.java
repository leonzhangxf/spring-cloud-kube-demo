package com.leonzhangxf.cloud.kube.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.CollectionUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.List;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@Slf4j
@SpringCloudApplication
@EnableScheduling
@EnableSwagger2WebMvc
public class AccountDistApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountDistApplication.class).run(args);
    }

    private DiscoveryClient discoveryClient;

    @Override
    public void run(String... args) throws Exception {
        log.info("services {}", discoveryClient.getServices());
        List<ServiceInstance> gatewayInstances = discoveryClient.getInstances("gateway-dist");
        if (!CollectionUtils.isEmpty(gatewayInstances)) {
            ServiceInstance serviceInstance = gatewayInstances.get(0);
            log.info("==== {}", serviceInstance.getUri().toString());
            log.info("==== {}", serviceInstance.getServiceId());
            log.info("==== {}", serviceInstance.getInstanceId());
            log.info("==== {}", serviceInstance.getScheme());
            log.info("==== {}", serviceInstance.getHost());
            log.info("==== {}", serviceInstance.getPort());
            log.info("==== {}", serviceInstance.getMetadata());
        }
    }

    @Autowired
    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }
}
