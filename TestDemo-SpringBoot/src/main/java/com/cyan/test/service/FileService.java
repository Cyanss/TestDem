package com.cyan.test.service;

import com.cyan.test.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/15
 */
public interface FileService {
    void fileUpload(MultipartFile file);
    void fileDownload(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
    List<FileInfo> getFileName();
}
