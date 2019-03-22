package com.cyan.test.dao;

import com.cyan.test.dto.UserDTO;
import com.cyan.test.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/13
 */
@Service
public interface UserInfoDAO {
    /** 查询所有 */
    List<UserVO> queryUserInfo();
    /** 查询单个 */
    UserVO queryUserInfoByUserId(Integer userId);
    /** 通过用户名查询 */
    UserVO queryUserInfoByName(String userName);
    /** 增加 */
    void insertUserInfo(UserDTO userDTO);
    /** 删除 */
    void deleteUserInfo(Integer userId);
    /** 修改 */
    void updateUserInfo(@Param("userId")Integer userId,@Param("userDTO") UserDTO userDTO);

}
