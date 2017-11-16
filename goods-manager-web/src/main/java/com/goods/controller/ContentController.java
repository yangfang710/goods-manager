package com.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goods.common.pojo.EUDatagridResult;
import com.goods.common.utils.GoodsResult;
import com.goods.pojo.TbContent;
import com.goods.service.ContentService;

/**
 * 内容管理
 * 
 * @author dell
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	@RequestMapping("/save")
	@ResponseBody
	public GoodsResult insertContent(TbContent content) {
		GoodsResult result = contentService.insertContent(content);
		return result;
	}

	@RequestMapping("/query/list")
	@ResponseBody
	public EUDatagridResult getContentList(@RequestParam(value = "categoryId", defaultValue = "0") Long categoryId) {
		EUDatagridResult result = contentService.getContentListById(categoryId);
		return result;
	}

}
