package com.goods.service;

import com.goods.common.pojo.EUDatagridResult;

public interface ItemParamItemService {
	String getItemParamByItemId(Long itemId);

	EUDatagridResult getItemParamItemList(Integer page, Integer rows);
}
