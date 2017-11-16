package com.goods.service;

import com.goods.common.pojo.EUDatagridResult;
import com.goods.common.utils.GoodsResult;
import com.goods.pojo.TbContent;

public interface ContentService {
	GoodsResult insertContent(TbContent content);

	EUDatagridResult getContentListById(long categoryId);
}
