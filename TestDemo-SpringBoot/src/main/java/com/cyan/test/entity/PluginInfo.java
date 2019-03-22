package com.cyan.test.entity;

import java.util.Date;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
public class PluginInfo {
    private Integer pluginId;
    /** 用户编号，外键关系在业务逻辑层处理 */
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
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    public Integer getPluginId() {
        return pluginId;
    }

    public void setPluginId(Integer pluginId) {
        this.pluginId = pluginId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getPluginType() {
        return pluginType;
    }

    public void setPluginType(String pluginType) {
        this.pluginType = pluginType;
    }

    public String getPluginIcon() {
        return pluginIcon;
    }

    public void setPluginIcon(String pluginIcon) {
        this.pluginIcon = pluginIcon;
    }

    public String getPluginEnv() {
        return pluginEnv;
    }

    public void setPluginEnv(String pluginEnv) {
        this.pluginEnv = pluginEnv;
    }

    public String getPluginKeyword() {
        return pluginKeyword;
    }

    public void setPluginKeyword(String pluginKeyword) {
        this.pluginKeyword = pluginKeyword;
    }

    public String getPluginUrl() {
        return pluginUrl;
    }

    public void setPluginUrl(String pluginUrl) {
        this.pluginUrl = pluginUrl;
    }

    public String getPluginContent() {
        return pluginContent;
    }

    public void setPluginContent(String pluginContent) {
        this.pluginContent = pluginContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
