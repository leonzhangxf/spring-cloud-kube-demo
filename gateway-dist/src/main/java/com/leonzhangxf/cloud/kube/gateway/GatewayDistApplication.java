package com.leonzhangxf.cloud.kube.gateway;

import com.leonzhangxf.cloud.kube.account.api.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

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
public class GatewayDistApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayDistApplication.class).run(args);
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
