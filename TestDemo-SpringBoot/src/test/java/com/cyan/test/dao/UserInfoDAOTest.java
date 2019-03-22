package com.cyan.test.dao;

import com.cyan.test.entity.UserInfo;
import com.cyan.test.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoDAOTest {
    @Autowired
    private UserInfoDAO userInfoDAO;

    @Test
    public void queryUserInfo() {
        List<UserVO> userInfos = userInfoDAO.queryUserInfo();
        Assert.notEmpty(userInfos);
    }

    @Test
    public void queryUserInfoByUserId() {
        UserVO userVO = userInfoDAO.queryUserInfoByUserId(6);
        Assert.notNull(userVO);
    }

    @Test
    public void queryUserInfoByName() {

    }

    @Test
    public void insertUserInfo() {

    }

    @Test
    public void deleteUserInfo() {
    }

    @Test
    public void updateUserInfo() {
    }
}