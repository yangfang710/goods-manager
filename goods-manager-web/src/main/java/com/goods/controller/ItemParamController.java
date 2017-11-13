package com.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goods.common.utils.GoodsResult;
import com.goods.pojo.TbItemParam;
import com.goods.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public GoodsResult getItemParamByCid(@PathVariable Long itemCatId) {
		GoodsResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}

	@RequestMapping("/save/{cid}")
	@ResponseBody
	public GoodsResult insertItemParam(@PathVariable Long cid, String paramData) {
		// 创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		GoodsResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}

}
