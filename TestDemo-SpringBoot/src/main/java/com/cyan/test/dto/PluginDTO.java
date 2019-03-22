package com.cyan.test.dto;

import lombok.Data;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Data
public class PluginDTO {
    /** 用户编号 */
    private Integer userId;
    /** 插件名称 */
    private String pluginName;
    /** 插件类型 */
    private String pluginType;
    /** 插件图标 */
    private String pluginIcon;
    /** 插件环境 */
    private String pluginEnv;
    /** 插件关键字 */
    private String pluginKeyword;
    /** 插件地址 */
    private String pluginUrl;
    /** 插件内容描述 */
    private String pluginContent;
}
