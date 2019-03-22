package com.cyan.test.dto;

import lombok.Data;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Data
public class UserDTO {
    /** 用户名称 */
    private String userName;
    /** 用户手机号 */
    private String userPhone;
    /** 用户邮箱 */
    private String userEmail;
    /** 用户密码 */
    private String userPassword;
    /** 用户权限 */
    private String userAuth;
}
