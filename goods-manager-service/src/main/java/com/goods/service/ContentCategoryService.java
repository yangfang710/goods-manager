package com.goods.service;

import java.util.List;

import com.goods.common.pojo.EUTreeNode;
import com.goods.common.utils.GoodsResult;

public interface ContentCategoryService {
	List<EUTreeNode> getCategoryList(long parentId);

	GoodsResult insertContentCategory(long parentId, String name);
}
