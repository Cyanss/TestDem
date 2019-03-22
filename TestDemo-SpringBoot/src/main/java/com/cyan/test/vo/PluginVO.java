package com.cyan.test.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Data
public class PluginVO {
    /** 插件编号 */
    @JsonProperty("pluginid")
    private Integer pluginId;
    /** 用户编号 */
    @JsonProperty("userid")
    private Integer userId;
    /** 插件名称 */
    @JsonProperty("pluginname")
    private String pluginName;
    /** 插件类型 */
    @JsonProperty("type")
    private String pluginType;
    /** 插件图标 */
    @JsonProperty("icon")
    private String pluginIcon;
    /** 插件环境 */
    @JsonProperty("env")
    private String pluginEnv;
    /** 插件关键字 */
    @JsonProperty("keyword")
    private String pluginKeyword;
    /** 插件地址 */
    @JsonProperty("url")
    private String pluginUrl;
    /** 插件内容描述 */
    @JsonProperty("content")
    private String pluginContent;
    /** 创建时间 */
    @JsonProperty("createtime")
    private Date createTime;


}
