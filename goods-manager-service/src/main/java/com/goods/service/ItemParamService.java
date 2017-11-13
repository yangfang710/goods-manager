package com.goods.service;

import com.goods.common.utils.GoodsResult;
import com.goods.pojo.TbItemParam;

public interface ItemParamService {
	GoodsResult getItemParamByCid(long cid);

	GoodsResult insertItemParam(TbItemParam itemParam);

}
