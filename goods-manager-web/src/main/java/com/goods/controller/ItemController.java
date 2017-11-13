package com.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goods.common.pojo.EUDatagridResult;
import com.goods.common.utils.GoodsResult;
import com.goods.pojo.TbItem;
import com.goods.pojo.TbItemDesc;
import com.goods.service.ItemService;

/**
 * 商品管理
 * 
 * @author dell
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EUDatagridResult getItemList(Integer page, Integer rows) {
		EUDatagridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping("/item/save")
	@ResponseBody
	public GoodsResult addItem(TbItem item, String desc, String itemParams) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		GoodsResult result = itemService.addItem(item, itemDesc, itemParams);
		return result;
	}

}
