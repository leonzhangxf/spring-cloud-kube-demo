package com.leonzhangxf.cloud.kube.gateway.api;

import com.leonzhangxf.cloud.kube.account.api.AccountApi;
import com.leonzhangxf.cloud.kube.account.domain.Account;
import com.leonzhangxf.cloud.kube.gateway.configuration.GatewayConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@Slf4j
@RestController
@Api(tags = "TEST")
public class TestApi {

    private AccountApi accountApi;

    private GatewayConfiguration gatewayConfiguration;

    @ApiOperation("test account")
    @GetMapping("/accounts")
    public Mono<List<Account>> getAccounts() {
        ResponseEntity<List<Account>> response = accountApi.getAccounts();
        log.info("the status {}", response.getStatusCodeValue());
        return Mono.justOrEmpty(response.getBody());
    }

    @ApiOperation("test config")
    @GetMapping("/configs")
    public Mono<String> getConfigs() {
        return Mono.justOrEmpty(gatewayConfiguration.getMessage());
    }

    @Autowired
    public void setAccountApi(AccountApi accountApi) {
        this.accountApi = accountApi;
    }

    @Autowired
    public void setGatewayConfiguration(GatewayConfiguration gatewayConfiguration) {
        this.gatewayConfiguration = gatewayConfiguration;
    }
}
