package com.cyan.test.service;

import com.cyan.test.dto.PluginDTO;
import com.cyan.test.entity.PluginInfo;
import com.cyan.test.vo.PluginAndUserVO;
import com.cyan.test.vo.PluginVO;
import com.cyan.test.vo.UserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
public interface PluginService {
    /** 查询单个 */
    PluginVO findPluginOne(Integer pluginId);
    /** 查询所有 */
    PageInfo<PluginVO> findPluginAll(Integer page, Integer size);
    /** 增加 */
    void addPlugin(PluginDTO pluginDTO);
    /** 删除 */
    void deletePluginOne(Integer pluginId);
    /** 修改 */
    void updatePlugin(Integer pluginId, PluginDTO pluginDTO);
    /** 通过插件编号查询所有信息 */
    PluginAndUserVO findPluginAndUser(Integer pluginId);
    /** 通过插件编号查询用户信息 */
    UserVO findUserByPluginId(Integer pluginId);
    /** 通过用户编号查找插件信息 */
    List<PluginVO> findByUserId(Integer userId);
    /** 通过插件名称模糊查找 */
    PageInfo<PluginVO> findByPluginNameLike(String pluginName, Integer page, Integer size);
}
