package com.goinghugh.controller.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>文件上传controller</p>
 *
 * @author wangyongxu
 * @date 2019/6/15 15:03
 */
@RestController
@RequestMapping("/file")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/upload")
    public ResponseEntity simpleUpload(@RequestParam("file") MultipartFile multipartFile) {
        logger.info("文件上传, {}", multipartFile);
        return ResponseEntity.ok().build();
    }
}
