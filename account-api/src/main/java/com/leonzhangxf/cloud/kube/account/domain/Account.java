package com.leonzhangxf.cloud.kube.account.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author leonzhangxf
 * @date 20190718
 */
@Data
@ApiModel("账户信息")
public class Account {

    @ApiModelProperty("账户ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
