package com.jhcz.trade.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import java.util.ArrayList;

/**
 * @类名: FileUploadController
 * @包名 com.jhcz.trade.common.controller
 * @描述: 上传控制器
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-28 下午7:44:34
 * @版本 V1.0
 */
@Controller
@RequestMapping("/upload.do")
public class FileUploadController {
	//http://www.2cto.com/kf/201412/360788.html
	/**
	  * @方法名: doUploadImg
	  * @描述: 图片上传
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-3-28下午7:25:34
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params = "method=doUploadImg")
	public ModelAndView doUploadImg(HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView(new MappingJacksonJsonView(),new HashMap<String, String>()) ;
	}
	
	

	 //单文件上传
	 @ResponseBody
     @RequestMapping(params = "method=uploadData")
     public ModelAndView uploadData(@RequestParam("uploadfile") CommonsMultipartFile file,HttpServletRequest request) {
         // MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
		 Map<String, Object> map = new HashMap<String, Object>();
		 List<String> pathList = new ArrayList<String>();
		 String key = request.getParameter("key");
         if (!file.isEmpty()) {
             String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
             String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
             String filepath = request.getContextPath()+"/upload/" + filename;
             String path = request.getSession().getServletContext().getRealPath(filepath);// 存放位置  //上传到服务器上的图片
             
             pathList.add(filepath);
             File destFile = new File(path);
             try {
                 // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                 //FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
            	 FileUtils.writeByteArrayToFile(destFile, file.getBytes());// 复制临时文件到指定目录下
             } catch (IOException e) {
            	 map.put("data", "-1");
            	 map.put("img_path", null);
                 e.printStackTrace();
             }
             map.put("data", "0");
             map.put("img_path", pathList);
         } else {
        	 map.put("data", "-1");
        	  map.put("img_path", null);
         }
         map.put("key", key);
         return new ModelAndView(new MappingJacksonJsonView(),map) ;
     }
     
     
	 //多文件上传
	 @ResponseBody
     @RequestMapping(params = "method=uploadDatas")
     public ModelAndView uploadDatas(@RequestParam("uploadfile") CommonsMultipartFile[] files,HttpServletRequest request) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 List<String> pathList = new ArrayList<String>();
		 String key = request.getParameter("key");
          if (files != null) {
              for (int i = 0; i < files.length; i++) {
                  String type = files[i].getOriginalFilename().substring( files[i].getOriginalFilename().indexOf("."));// 取文件格式后缀名
                  String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
                  String filepath = request.getContextPath()+"/upload/" + filename;
                  String path = request.getSession().getServletContext().getRealPath(filepath);// 存放位置
                  pathList.add(filepath);
                  File destFile = new File(path);
                  try {
                      //FileUtils.copyInputStreamToFile(files[i].getInputStream(),destFile);// 复制临时文件到指定目录下
                	  FileUtils.writeByteArrayToFile(destFile, files[i].getBytes());// 复制临时文件到指定目录下
                  } catch (IOException e) {
                	  map.put("data", "-1");
                	  map.put("img_path", null);
                      e.printStackTrace();
                  }
              }
              map.put("data", "0");
              map.put("img_path", pathList);
          } else {
        	  map.put("data", "-1");
          }
          map.put("key", key);
          return new ModelAndView(new MappingJacksonJsonView(),map) ;
      }
}
