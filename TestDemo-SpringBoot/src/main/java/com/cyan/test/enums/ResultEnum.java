package com.cyan.test.enums;

import lombok.Getter;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Getter
public enum ResultEnum {
    DATA_IS_EMPTY(1,"查询数据为空"),
    PARAM_ERROR(2, "参数不正确"),
    USER_INFO_NOT_EXIT(3, "用户信息不存在"),
    USER_NAME_IS_EXIT(4, "用户名已存在"),
    PLUGIN_INFO_NOT_EXIT(5, "插件信息不存在"),
    PLUGIN_NAME_IS_EXIT(6, "插件名已存在"),
    PLUGIN_NAME_IS_NULL(7,"插件名不能为空"),
    USER_PHONE_IS_NULL(8,"手机号码不能为空"),
    USER_NAME_IS_NULL(9,"用户名不能为空"),
    USER_DELETE_ILLEGAL_OPERATION(10, "请先删除该用户的上传的插件信息"),
    PLUGIN_TYPE_IS_NULL(11, "插件类型不能为空"),
    PLUGIN_KEYWORD_IS_NULL(12, "插件关键字不能为空"),
    FILE_IS_NULL(13, "上传文件不能为空"),
    FILE_UPLOAD_FAIL(14, "上传文件失败"),
    FILE_DOWNLOAD_FAIL(15, "下载文件失败"),
    NO_FILE_DOWNLOAD(16, "没有文件可下载"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
