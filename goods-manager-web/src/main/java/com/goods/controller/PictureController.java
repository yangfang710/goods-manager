package com.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goods.common.utils.PictureResult;
import com.goods.service.PictureService;

/**
 * 图片上传管理
 * 
 * @author dell
 *
 */
@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile) {
		PictureResult result = pictureService.uploadPicture(uploadFile);
		return result;
	}

}
