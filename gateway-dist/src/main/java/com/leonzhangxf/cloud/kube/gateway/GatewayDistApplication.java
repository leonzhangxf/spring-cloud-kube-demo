package com.leonzhangxf.cloud.kube.gateway;

import com.leonzhangxf.cloud.kube.account.api.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.List;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@Slf4j
@SpringCloudApplication
@EnableFeignClients(basePackageClasses = Api.class)
@EnableScheduling
@EnableSwagger2WebFlux
@Controller
public class GatewayDistApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayDistApplication.class).run(args);
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    private DiscoveryClient discoveryClient;

    @Override
    public void run(String... args) throws Exception {
        log.info("services {}", discoveryClient.getServices());
        List<ServiceInstance> instances = discoveryClient.getInstances("account-dist");
        if (!CollectionUtils.isEmpty(instances)) {
            for (ServiceInstance serviceInstance : instances) {
                log.info("Get the account-dist pod.");
                log.info("The pod serviceId:{},\n uri:{},\n instanceId:{},\n metadata:{}.",
                    serviceInstance.getServiceId(), serviceInstance.getUri(),
                    serviceInstance.getInstanceId(), serviceInstance.getMetadata());
            }
        }
    }

    @Autowired
    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }
}
