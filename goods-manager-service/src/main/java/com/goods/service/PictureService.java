package com.goods.service;

import org.springframework.web.multipart.MultipartFile;

import com.goods.common.utils.PictureResult;

public interface PictureService {
	PictureResult uploadPicture(MultipartFile uploadFile);
}
