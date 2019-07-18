package com.leonzhangxf.cloud.kube.account.api;

import com.google.common.collect.Lists;
import com.leonzhangxf.cloud.kube.account.domain.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@Api(tags = "账户API")
@RestController
public class AccountApiImpl implements AccountApi {

    @ApiOperation("查询账户信息")
    @GetMapping("/accounts")
    @Override
    public ResponseEntity<List<Account>> getAccounts() {
        Account account1 = new Account();
        account1.setId(1);
        account1.setUsername("leon");
        account1.setPassword("123456");

        Account account2 = new Account();
        account2.setId(1);
        account2.setUsername("ding");
        account2.setPassword("55555");

        Account account3 = new Account();
        account3.setId(1);
        account3.setUsername("li");
        account3.setPassword("23333");
        return ResponseEntity.ok(Lists.newArrayList(account1, account2, account3));
    }
}
