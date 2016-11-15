package com.yizhishang.plat.web.action;

import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: UploadFileAction.java文件传输
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-1-7
 * 创建时间: 上午1:32:05
 */
@Controller
@RequestMapping("/admin/UploadFileAdmin")
public class UploadFileAction extends BaseAction
{

    private final Logger logger = LoggerFactory.getLogger(UploadFileAction.class);

    /**
     * 描述: fileUpload  上传文件
     * 作者: 袁永君
     * 创建日期: 2016-1-7
     * 创建时间: 上午1:31:27
     *
     * @param request
     * @return result
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/fileUpload.action", produces = "application/json;charset=utf-8;")
    public Result fileUpload(HttpServletRequest request) throws IOException
    {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获得文件：
        List<MultipartFile> uploadfile = multipartRequest.getFiles("uploadfile");
        if (uploadfile != null && uploadfile.size() > 0) {
            String realPath = request.getSession().getServletContext().getRealPath("/");
            for (MultipartFile file : uploadfile) {
                if (file.isEmpty()) {
                    return new Result(-1, "请选择文件后上传");
                } else {
                    String fileName = file.getOriginalFilename();
                    String name = file.getName();
                    String type = file.getContentType();
                    Long size = file.getSize();
                    System.out.println("文件原名: " + fileName);
                    System.out.println("文件名称: " + name);
                    System.out.println("文件长度: " + size);
                    System.out.println("文件类型: " + type);
                    System.out.println("========================================");
                    try {
                        String savePath = realPath + "/upload/" + fileName;

                        FileCopyUtils.copy(file.getBytes(), new File(savePath));
                        result.setErrorNo(0);
                        result.setErrorInfo("上传成功!");

                        Map<String, Object> fileData = new HashMap<String, Object>();
                        fileData.put("name", name);
                        fileData.put("path", "/upload/" + fileName);
                        fileData.put("size", size);
                        fileData.put("type", type);

                        result.setObj(fileData);
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                        addLog("上传图片失败", e.getMessage());
                        result.setErrorNo(-1);
                        result.setErrorInfo(e.getMessage());
                    }
                }
            }
            return result;
        } else {
            return new Result(-1, "请选择文件后上传");
        }
    }

    /**
     * 描述: deleteFile删除文件
     * 作者: 袁永君
     * 创建日期: 2016-1-7
     * 创建时间: 上午1:29:08
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteFile.action")
    public Result deleteFile()
    {
        String filePath = getStrParameter("filePath");
        if (StringHelper.isEmpty(filePath)) {
            return new Result(-1, "需要删除的文件不存在");
        }

        String realPath = getSession().getServletContext().getRealPath("/");
        String fileAbsPath = realPath + filePath;
        fileAbsPath = FileHelper.normalize(fileAbsPath);
        if (!FileHelper.isFile(fileAbsPath)) {
            return new Result(-1, "需要删除的文件不存在");
        }

        FileHelper.deleteFile(fileAbsPath);
        result.setErrorNo(0);
        result.setErrorInfo("成功删除文件" + fileAbsPath);
        return result;
    }

    /***
     * 读取上传文件中得所有文件并返回
     *
     * @return
     */
    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request)
    {
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        ModelAndView mav = new ModelAndView("list");
        File uploadDest = new File(filePath);
        String[] fileNames = uploadDest.list();
        for (int i = 0; i < fileNames.length; i++) {
            //打印出文件名
            System.out.println(fileNames[i]);
        }
        return mav;
    }
}
