package com.goods.service;

import com.goods.common.pojo.EUDatagridResult;
import com.goods.common.utils.GoodsResult;
import com.goods.pojo.TbItem;
import com.goods.pojo.TbItemDesc;

public interface ItemService {
	TbItem getItemById(long itemId);

	EUDatagridResult getItemList(int page, int rows);

	GoodsResult addItem(TbItem item, TbItemDesc itemDesc, String itemParam);
}
