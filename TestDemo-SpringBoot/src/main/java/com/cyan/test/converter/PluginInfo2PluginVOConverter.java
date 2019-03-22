package com.cyan.test.converter;

import com.cyan.test.entity.PluginInfo;
import com.cyan.test.vo.PluginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/12
 */
@Slf4j
public class PluginInfo2PluginVOConverter {
    public static PluginVO convert(PluginInfo pluginInfo) {
        PluginVO pluginVO = new PluginVO();
        BeanUtils.copyProperties(pluginInfo,pluginVO);
        return pluginVO;
    }
    public static List<PluginVO> convert(List<PluginInfo> pluginInfoList) {
        return pluginInfoList.stream().map(pluginInfo ->
                convert(pluginInfo)
        ).collect(Collectors.toList());
    }
}
