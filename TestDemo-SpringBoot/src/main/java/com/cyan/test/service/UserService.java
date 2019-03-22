package com.cyan.test.service;

import com.cyan.test.dto.UserDTO;
import com.cyan.test.vo.UserVO;
import com.github.pagehelper.PageInfo;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
public interface UserService {
    /** 查询单个 */
    UserVO findUserOne(Integer userId);
    /** 查询所有 */
    PageInfo<UserVO> findUserAll(Integer page, Integer size);
    /** 增加 */
    void addUser(UserDTO userDTO);
    /** 删除 */
    void deleteUserOne(Integer userId);
    /** 修改 */
    void updateUser(Integer userId, UserDTO userDTO);
    /** 通过用户名查询 */
    UserVO findUserByName(String userName);
}
