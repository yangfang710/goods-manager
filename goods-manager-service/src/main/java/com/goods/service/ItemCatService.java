package com.goods.service;

import java.util.List;

import com.goods.common.pojo.TreeNode;

public interface ItemCatService {
	List<TreeNode> getItemCatList(long parentId);
}
