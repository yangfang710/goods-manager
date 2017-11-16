package com.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goods.common.pojo.EUTreeNode;
import com.goods.common.utils.GoodsResult;
import com.goods.service.ContentCategoryService;

/**
 * 内容分类管理
 * 
 * @author dell
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}

	@RequestMapping("/create")
	@ResponseBody
	public GoodsResult createContentCategory(Long parentId, String name) {
		GoodsResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}

}
