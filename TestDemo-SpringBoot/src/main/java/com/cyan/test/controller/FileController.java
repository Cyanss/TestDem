package com.cyan.test.controller;

import com.cyan.test.entity.FileInfo;
import com.cyan.test.enums.ResultEnum;
import com.cyan.test.exception.TestException;
import com.cyan.test.service.FileService;
import com.cyan.test.util.ResultVOUtil;
import com.cyan.test.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/15
 */
@RestController
@RequestMapping("/file")
@Slf4j
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;

    @ResponseBody
    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file") MultipartFile file) {
        fileService.fileUpload(file);
        return ResultVOUtil.success("上传成功");
    }

    @GetMapping("/list")
    public ResultVO<List<String>> getFileName(){
        List<FileInfo> fileInfoList = fileService.getFileName();
        return ResultVOUtil.success(fileInfoList);
    }

    @RequestMapping("/download")
    public void downloadFile(@RequestParam("filename") String fileName, HttpServletRequest request,
                               HttpServletResponse response) throws UnsupportedEncodingException {
        fileService.fileDownload(fileName, request, response);
//        return ResultVOUtil.success("下载成功");
    }
}
