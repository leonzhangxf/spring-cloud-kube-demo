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

    @ApiOperation(value = "Test invoke account service", notes = "此接口调用通过服务注册发现、负载均衡机制调用account服务")
    @GetMapping("/accounts")
    public Mono<List<Account>> getAccounts() {
        ResponseEntity<List<Account>> response = accountApi.getAccounts();
        log.info("the status {}", response.getStatusCodeValue());
        return Mono.justOrEmpty(response.getBody());
    }

    @ApiOperation(value = "Test config", notes = "此接口用于演示基于kubernetes的中心化配置功能，预计ConfigMap，" +
        "可以通过kubernetes proxy启动后启动的administration webUI进行配置更新，或者通过命令更新ConfigMap，" +
        "再通过此接口查看配置更新")
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
