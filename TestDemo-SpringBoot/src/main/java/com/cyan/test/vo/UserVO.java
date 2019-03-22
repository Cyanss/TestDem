package com.cyan.test.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Data
public class UserVO {
    /** 用户编号 */
    @JsonProperty("userid")
    private Integer userId;
    /** 用户名称 */
    @JsonProperty("username")
    private String userName;
    /** 用户手机号 */
    @JsonProperty("phone")
    private String userPhone;
    /** 用户邮箱 */
    @JsonProperty("email")
    private String userEmail;
    /** 用户密码 */
    @JsonProperty("password")
    private String userPassword;
    /** 用户权限 */
    @JsonProperty("auth")
    private String userAuth;
    /** 创建时间 */
    @JsonProperty("createtime")
    private Date createTime;
}
