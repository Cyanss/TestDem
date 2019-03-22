package com.cyan.test.service.Impl;

import com.cyan.test.converter.UserInfo2UserVoConverter;
import com.cyan.test.dao.UserInfoDAO;
import com.cyan.test.dto.UserDTO;
import com.cyan.test.entity.PluginInfo;
import com.cyan.test.entity.UserInfo;
import com.cyan.test.enums.ResultEnum;
import com.cyan.test.exception.TestException;
import com.cyan.test.service.PluginService;
import com.cyan.test.service.UserService;
import com.cyan.test.util.CopyUtils;
import com.cyan.test.vo.PluginVO;
import com.cyan.test.vo.UserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDAO userInfoDAO;
    @Autowired
    private PluginService pluginService;

    @Override
    public UserVO findUserOne(Integer userId) {
        return findOne(userId);
    }

    @Override
    public PageInfo<UserVO> findUserAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<UserVO> userVOList = userInfoDAO.queryUserInfo();
        return new PageInfo<>(userVOList);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        if (userDTO.getUserName() == null) {
            log.error("【用户名不能为空】");
            throw new TestException(ResultEnum.USER_NAME_IS_NULL);
        }
        checkName(userDTO.getUserName());
        checkPhone(userDTO);
        userInfoDAO.insertUserInfo(userDTO);
    }

    @Override
    public void deleteUserOne(Integer userId) {
        findOne(userId);
        /** 处理外键关系 */
        List<PluginVO> pluginVOList = pluginService.findByUserId(userId);
        if (pluginVOList.size() > 0) {
            log.error("【该用户存在插件信息不可删除】");
            throw new TestException(ResultEnum.USER_DELETE_ILLEGAL_OPERATION);
        }
        userInfoDAO.deleteUserInfo(userId);
    }

    @Override
    public void updateUser(Integer userId, UserDTO userDTO) {

        findOne(userId);
        if (userDTO.getUserName() != null){
            checkName(userDTO.getUserName());
        }
        userInfoDAO.updateUserInfo(userId, userDTO);
    }

    @Override
    public UserVO findUserByName(String userName) {
        UserVO userVO = userInfoDAO.queryUserInfoByName(userName);
        if (userVO == null) {
            log.error("【用户信息不存在】");
            throw new TestException(ResultEnum.USER_INFO_NOT_EXIT);
        }
        return userVO;
    }

    private UserVO findOne(Integer userId){
        UserVO userVO = userInfoDAO.queryUserInfoByUserId(userId);
        if (userVO == null) {
            log.error("【用户信息不存在】");
            throw new TestException(ResultEnum.USER_INFO_NOT_EXIT);
        }
        return userVO;
    }

    private void checkName(String userName){
        UserVO userVO = userInfoDAO.queryUserInfoByName(userName);
        if (userVO != null) {
            log.error("【用户名已存在】");
            throw new TestException(ResultEnum.USER_NAME_IS_EXIT);
        }
    }

    private void checkPhone(UserDTO userDTO) {
        if (userDTO.getUserPhone() == null) {
            log.error("【手机号不能为空】");
            throw new TestException(ResultEnum.USER_PHONE_IS_NULL);
        }
    }
}
