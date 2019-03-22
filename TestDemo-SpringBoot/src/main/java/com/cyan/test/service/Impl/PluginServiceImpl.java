package com.cyan.test.service.Impl;

import com.cyan.test.dao.PluginInfoDAO;
import com.cyan.test.dto.PluginDTO;
import com.cyan.test.enums.ResultEnum;
import com.cyan.test.exception.TestException;
import com.cyan.test.service.PluginService;
import com.cyan.test.service.UserService;
import com.cyan.test.vo.PluginAndUserVO;
import com.cyan.test.vo.PluginVO;
import com.cyan.test.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Service
@Slf4j
public class PluginServiceImpl implements PluginService {
    @Autowired
    private PluginInfoDAO pluginInfoDAO;
    @Autowired
    private UserService userService;

    @Override
    public PluginVO findPluginOne(Integer pluginId) {
        return findOne(pluginId);
    }

    @Override
    public PageInfo<PluginVO> findPluginAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<PluginVO> pluginVOList = pluginInfoDAO.queryPluginInfo();
        return new PageInfo<>(pluginVOList);

    }

    @Override
    public void addPlugin(PluginDTO pluginDTO) {
        checkPluginContent(pluginDTO);
        if (pluginDTO.getPluginName() == null) {
            log.error("【插件名不能为空】");
            throw new TestException(ResultEnum.PLUGIN_NAME_IS_NULL);
        }
        /** 处理外键关系 */
        UserVO userVO = userService.findUserOne(pluginDTO.getUserId());
        if (userVO == null) {
            log.error("【用户信息不存在】");
            throw new TestException(ResultEnum.USER_INFO_NOT_EXIT);
        }
        PluginVO result = pluginInfoDAO.queryPluginInfoByName(pluginDTO.getPluginName());
        if (result != null) {
            log.error("【插件名已存在】");
            throw new TestException(ResultEnum.PLUGIN_NAME_IS_EXIT);
        }
        pluginInfoDAO.insertPluginInfo(pluginDTO);
    }

    @Override
    public void deletePluginOne(Integer pluginId) {
        findOne(pluginId);
        pluginInfoDAO.deletePluginInfo(pluginId);
    }

    @Override
    public void updatePlugin(Integer pluginId, PluginDTO pluginDTO) {
        findOne(pluginId);
        pluginInfoDAO.updatePluginInfo(pluginId, pluginDTO);
    }

    @Override
    public PluginAndUserVO findPluginAndUser(Integer pluginId) {
        PluginVO pluginVO = findOne(pluginId);
        PluginAndUserVO pluginAndUserVO = new PluginAndUserVO();
        BeanUtils.copyProperties(pluginVO, pluginAndUserVO);
        pluginAndUserVO.setPluginCreate(pluginVO.getCreateTime());
        UserVO userVO = userService.findUserOne(pluginVO.getUserId());
        BeanUtils.copyProperties(userVO, pluginAndUserVO);
        pluginAndUserVO.setUserCreate(userVO.getCreateTime());
        return pluginAndUserVO;
    }

    @Override
    public UserVO findUserByPluginId(Integer pluginId) {
        PluginVO pluginVO = findOne(pluginId);
        return userService.findUserOne(pluginVO.getUserId());
    }

    @Override
    public List<PluginVO> findByUserId(Integer userId) {
        return pluginInfoDAO.queryPluginInfoByUserId(userId);
    }

    @Override
    public PageInfo<PluginVO> findByPluginNameLike(String pluginName, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<PluginVO> pluginVOList = pluginInfoDAO.queryByPluginNameLike(pluginName);
        return new  PageInfo<>(pluginVOList);
    }

    private PluginVO findOne(Integer pluginId) {
        PluginVO pluginVO = pluginInfoDAO.queryPluginInfoById(pluginId);
        if (pluginVO == null) {
            log.error("【插件信息不存在】");
            throw new TestException(ResultEnum.PLUGIN_INFO_NOT_EXIT);
        }
        return pluginVO;
    }
    private void checkPluginContent(PluginDTO pluginDTO) {
        if (pluginDTO.getPluginType() == null) {
            log.error("【插件类型不能为空】");
            throw new TestException(ResultEnum.PLUGIN_TYPE_IS_NULL);
        }
        if (pluginDTO.getPluginKeyword() == null) {
            log.error("【插件关键字不能为空】");
            throw new TestException(ResultEnum.PLUGIN_KEYWORD_IS_NULL);
        }
    }
}
