package com.cyan.test.dao;

import com.cyan.test.dto.PluginDTO;
import com.cyan.test.entity.PluginInfo;
import com.cyan.test.vo.PluginVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/13
 */
@Service
public interface PluginInfoDAO {
    /** 查询单个 */
    PluginVO queryPluginInfoById(Integer pluginId);
    /** 查询所有 */
    List<PluginVO> queryPluginInfo();
    /** 增加 */
    void insertPluginInfo(PluginDTO pluginDTO);
    /** 删除 */
    void deletePluginInfo(Integer pluginId);
    /** 修改 */
    void updatePluginInfo(@Param("pluginId") Integer pluginId,@Param("pluginDTO") PluginDTO pluginDTO);
    /** 通过用户编号查找插件信息 */
    List<PluginVO> queryPluginInfoByUserId(Integer userId);
    /** 通过插件名称查找信息 */
    PluginVO queryPluginInfoByName(String pluginName);
    /** 通过插件名称模糊查找 */
    List<PluginVO> queryByPluginNameLike(String pluginName);
}
