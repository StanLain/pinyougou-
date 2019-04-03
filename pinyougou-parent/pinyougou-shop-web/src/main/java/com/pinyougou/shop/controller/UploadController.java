package com.pinyougou.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import entity.Result;
import utils.FastDFSClient;

@RestController
public class UploadController {
	@Value("${Flie_Service_URL}")
	private String file_url;
	
	@RequestMapping("/upload")
	public Result upload(MultipartFile file) {
		//获取上传文件的格式
		String originalFilename = file.getOriginalFilename();
		//获取后缀格式名
		String string = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			String uploadFile = fastDFSClient.uploadFile(file.getBytes(),string);
			//设置访问全路径
			String url=file_url+uploadFile;
			//System.out.println(url);
			//告诉前端访问全路径，访问路径显示图片
			return new Result(true, url);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "上传失败");
		}
	}
}
