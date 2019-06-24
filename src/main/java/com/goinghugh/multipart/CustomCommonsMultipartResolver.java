package com.goinghugh.multipart;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/**
 * <p>文件上传解析器</p>
 *
 * @author wangyongxu
 * @date : 2019-06-24 05:06:16
 * @since v1.0
 */
public class CustomCommonsMultipartResolver extends CommonsMultipartResolver {
    protected Set<String> illegalFileTypes = new HashSet<>();
    //上传失败错误码;
    protected int ulCode;
    //上传失败错误信息
    protected String ulMsg;
    //上传失败误信息UI提示
    protected String ulUiMsg;
    //非法文件类型错误码;
    protected int iftCode;
    protected String iftMsg;//非法文件类型错误信息;
    protected String iftUiMsg;//非法文件类型错误信息UI提示;
    protected int fsleCode;//文件大小操作上限错误码;
    protected String fsleMsg;//文件大小操作上限错误信息;
    protected String fsleUiMsg;//文件大小操作上限错误信息UI提示;


    public CustomCommonsMultipartResolver() {
        illegalFileTypes.add("exe");
        illegalFileTypes.add("bat");
        illegalFileTypes.add("sh");
        illegalFileTypes.add("reg");
    }

    @Override
    public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
        MultipartHttpServletRequest mpsRequest = null;
        logger.info("解析多块部分");
        try {
            mpsRequest = super.resolveMultipart(request);
        } catch (MultipartException e) {
            if (e instanceof MaxUploadSizeExceededException) {
                throw new RuntimeException("文件超过最大限制");
            }
            throw new RuntimeException(e);
        }
        checkFileType(mpsRequest);
        return mpsRequest;
    }

    protected void checkFileType(MultipartHttpServletRequest request) {
        Iterator<String> filenames = request.getFileNames();
        while (filenames.hasNext()) {
            String fileName = filenames.next();
            if (request.getMultiFileMap().containsKey(fileName)) {
                List<MultipartFile> files = request.getMultiFileMap().get(fileName);
                for (MultipartFile file : files) {
                    String extName = getExtName(file.getOriginalFilename());
                    logger.info("上传的文件名为：" + extName);
                    if (illegalFileTypes.contains(extName.toLowerCase())) {
                        throw new RuntimeException("非法类型");
                    }
                }
            }
        }
    }

    public static String getExtName(String fileName) {
        int pos = fileName.lastIndexOf(".");
        if (pos > -1) {
            return fileName.substring(pos + 1).toLowerCase();
        } else {
            return "";
        }
    }

    public void setUlCode(int ulCode) {
        this.ulCode = ulCode;
    }

    public void setUlMsg(String ulMsg) {
        this.ulMsg = ulMsg;
    }

    public void setUlUiMsg(String ulUiMsg) {
        this.ulUiMsg = ulUiMsg;
    }

    public void setIftCode(int iftCode) {
        this.iftCode = iftCode;
    }

    public void setIftMsg(String iftMsg) {
        this.iftMsg = iftMsg;
    }

    public void setIftUiMsg(String iftUiMsg) {
        this.iftUiMsg = iftUiMsg;
    }

    public void setFsleCode(int fsleCode) {
        this.fsleCode = fsleCode;
    }

    public void setFsleMsg(String fsleMsg) {
        this.fsleMsg = fsleMsg;
    }

    public void setFsleUiMsg(String fsleUiMsg) {
        this.fsleUiMsg = fsleUiMsg;
    }

}
