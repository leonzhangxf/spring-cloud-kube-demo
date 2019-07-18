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
    @GetMapping("accounts")
    @Override
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(Lists.newArrayList(
            Account.builder().id(1).username("leon").password("123456").build(),
            Account.builder().id(1).username("ding").password("55555").build(),
            Account.builder().id(1).username("li").password("23333").build()
        ));
    }
}
