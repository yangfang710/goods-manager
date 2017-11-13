package com.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goods.common.pojo.EUDatagridResult;
import com.goods.service.ItemParamItemService;

/**
 * 规格参数
 * 
 * @author dell
 *
 */
@Controller
public class ItemParamItemController {
	@Autowired
	private ItemParamItemService itemParamItemService;

	@RequestMapping("/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}

	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDatagridResult getItemParamList(Integer page, Integer rows) {
		EUDatagridResult result = itemParamItemService.getItemParamItemList(page, rows);
		return result;
	}
}
