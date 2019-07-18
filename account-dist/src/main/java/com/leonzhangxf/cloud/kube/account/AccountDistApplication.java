package com.leonzhangxf.cloud.kube.account;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@SpringCloudApplication
public class AccountDistApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountDistApplication.class).run(args);
    }
}
