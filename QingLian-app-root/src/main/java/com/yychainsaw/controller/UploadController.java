package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class UploadController {

    // 允许上传的文件类型白名单
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".webp");

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        try {
            String url = uploadSingleFile(file);
            // 单个上传通常由 Tomcat 线程处理
            log.info("单文件上传完成: {}", url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error(e.getMessage());
        }
    }

    /**
     * 新增：批量上传接口 (多线程并行)
     * 前端使用 FormData 传递多个文件，key 均为 "files"
     */
    @PostMapping("/upload/batch")
    public Result<List<String>> uploadBatch(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return Result.error("上传文件不能为空");
        }

        log.info("=== 开始批量上传 {} 个文件 ===", files.length);
        long startTime = System.currentTimeMillis();

        try {
            // 使用 parallelStream 自动利用 ForkJoinPool 进行多线程并发上传
            List<String> urls = Arrays.stream(files)
                    .parallel() // 开启并行流
                    .map(file -> {
                        try {
                            return uploadSingleFile(file);
                        } catch (Exception e) {
                            throw new RuntimeException("文件 " + file.getOriginalFilename() + " 上传失败: " + e.getMessage());
                        }
                    })
                    .collect(Collectors.toList());

            long endTime = System.currentTimeMillis();
            log.info("=== 批量上传结束，总耗时: {} ms ===", (endTime - startTime));

            return Result.success(urls);
        } catch (Exception e) {
            log.error("批量上传发生错误", e);
            return Result.error("批量上传失败，请检查文件格式或网络");
        }
    }

    private String uploadSingleFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        String originalFilename = file.getOriginalFilename();

        log.info(">>> 线程 [{}] 正在处理文件: {}", Thread.currentThread().getName(), originalFilename);

        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        }

        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("不支持的文件格式，仅支持图片");
        }

        String objectName = UUID.randomUUID().toString() + extension;

        return AliOssUtil.uploadFile(objectName, file.getInputStream());
    }
}
