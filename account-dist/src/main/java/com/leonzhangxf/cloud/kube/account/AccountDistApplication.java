package com.leonzhangxf.cloud.kube.account;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@SpringCloudApplication
@EnableScheduling
@EnableSwagger2WebMvc
public class AccountDistApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountDistApplication.class).run(args);
    }
}
