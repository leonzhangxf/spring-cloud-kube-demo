package com.leonzhangxf.cloud.kube.account.api;

import com.leonzhangxf.cloud.kube.account.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@FeignClient("account-dist.leonzhangxf.com")
@Component
public interface AccountApi {

    /**
     * 获取账户信息
     *
     * @return 账户信息
     */
    @GetMapping("accounts")
    ResponseEntity<List<Account>> getAccounts();
}
