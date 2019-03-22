package com.cyan.test.converter;

import com.cyan.test.entity.UserInfo;
import com.cyan.test.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/12
 */
@Slf4j
public class UserInfo2UserVoConverter {
    public static UserVO convert(UserInfo userInfo) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userInfo,userVO);
        return userVO;
    }
    public static List<UserVO> convert(List<UserInfo> userInfoList) {
        return userInfoList.stream().map(userInfo ->
                convert(userInfo)
        ).collect(Collectors.toList());
    }
}
