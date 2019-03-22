package com.cyan.test.controller;

import com.cyan.test.dto.PluginDTO;
import com.cyan.test.entity.PluginInfo;
import com.cyan.test.enums.ResultEnum;
import com.cyan.test.exception.TestException;
import com.cyan.test.service.PluginService;
import com.cyan.test.util.ResultVOUtil;
import com.cyan.test.vo.PluginAndUserVO;
import com.cyan.test.vo.PluginVO;
import com.cyan.test.vo.ResultVO;
import com.cyan.test.vo.UserVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@RestController
@RequestMapping("/plugin")
@Slf4j
@CrossOrigin
public class PluginController {
    @Autowired
    private PluginService pluginService;

    /**
     * 查询单个
     * @param pluginId
     * @return
     */
    @GetMapping("/one")
    public ResultVO<PluginVO> findPluginOne(@RequestParam("pluginid") Integer pluginId){
        PluginVO pluginVO = pluginService.findPluginOne(pluginId);
        return ResultVOUtil.success(pluginVO);
    };

    /**
     * 查询所有 分页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/page")
    public ResultVO<List<PluginVO>> findPluginAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageInfo<PluginVO> pageInfo = pluginService.findPluginAll(page, size);
        return ResultVOUtil.success(pageInfo);
    };

    /**
     * 增加信息
     * @param pluginDTO
     * @return
     */
    @PostMapping("/add")
    public ResultVO<PluginInfo> addPlugin(@RequestBody PluginDTO pluginDTO){
        pluginService.addPlugin(pluginDTO);
        return ResultVOUtil.success("添加成功");
    };

    /**
     * 删除
     * @param pluginId
     * @return
     */
    @DeleteMapping("/delete")
    public ResultVO deletePluginOne(@RequestParam("pluginid") Integer pluginId){
        pluginService.deletePluginOne(pluginId);
        return ResultVOUtil.success("删除成功");
    };

    /**
     * 更新
     * @param pluginId
     * @param PluginDTO
     * @return
     */
    @PutMapping("/update")
    public ResultVO<PluginInfo> updatePlugin(@RequestParam("pluginid") Integer pluginId,
                                             @RequestBody PluginDTO PluginDTO){
        pluginService.updatePlugin(pluginId, PluginDTO);
        return ResultVOUtil.success("更新成功");
    };

    /**
     * 通过插件编号查询所有信息
     * @param pluginId
     * @return
     */
    @GetMapping("/all")
    public ResultVO<PluginAndUserVO> findPluginAndUser(@RequestParam("pluginid") Integer pluginId){
        PluginAndUserVO pluginAndUserVO = pluginService.findPluginAndUser(pluginId);
        return ResultVOUtil.success(pluginAndUserVO);
    };

    /**
     * 通过插件编号查询用户信息
     * @param pluginId
     * @return
     */
    @GetMapping("/user")
    public ResultVO<UserVO> findUserByPluginId(@RequestParam("pluginid") Integer pluginId){
        UserVO userVO = pluginService.findUserByPluginId(pluginId);
        return ResultVOUtil.success(userVO);
    };

    /**
     * 模糊查找
     * @param pluginName
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/like")
    public ResultVO<List<PluginVO>> like(@RequestParam("pluginname") String pluginName,
                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(pluginName)) {
            log.error("【查询订单列表】pluginName");
            throw new TestException(ResultEnum.PARAM_ERROR);
        }
        PageInfo<PluginVO> pageInfo = pluginService.findByPluginNameLike(pluginName, page, size);

        return ResultVOUtil.success(pageInfo);
    }
}
