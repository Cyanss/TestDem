package com.cyan.test.service.Impl;

import com.cyan.test.entity.FileInfo;
import com.cyan.test.enums.ResultEnum;
import com.cyan.test.exception.TestException;
import com.cyan.test.service.FileService;
import com.cyan.test.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.*;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/15
 */
@Service
@Slf4j
public class FileServerImpl implements FileService {

    @Value("${upload.location.windows}")
    private String filePath;

    @Override
    public void fileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            log.info("【文件为空】");
            throw new TestException(ResultEnum.FILE_IS_NULL);
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error(e.toString(), e);
            throw new TestException(ResultEnum.FILE_UPLOAD_FAIL);
        }
    }

    @Override
    public List<FileInfo> getFileName() {
        File file = new File(filePath);
        List<File> fileList = Arrays.asList(Objects.requireNonNull(file.listFiles()));
        if (fileList.size() == 0) {
            log.error("【文件空间没有文件】");
            throw new TestException(ResultEnum.NO_FILE_DOWNLOAD);
        }
        return fileList.stream().map(item -> getFileInfo(item)).collect(Collectors.toList());
    }

    @Override
    public void fileDownload(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String realPath = filePath;
        File file = new File(realPath, fileName);
        if (!file.exists()) {
            log.error("【文件下载失败】");
            throw new TestException(ResultEnum.NO_FILE_DOWNLOAD);
        }
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        // 实现文件下载
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        catch (Exception e) {
            log.error("【文件下载失败】");
            throw new TestException(ResultEnum.FILE_DOWNLOAD_FAIL);
        }
        finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private FileInfo getFileInfo(File file) {
        if (!file.exists() || !file.isFile()){
            log.error("【文件空间没有文件】");
            throw new TestException(ResultEnum.NO_FILE_DOWNLOAD);
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(file.getName());
        fileInfo.setFilePath(file.getPath());
        return fileInfo;
    }
}
