package com.goods.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.common.utils.GoodsResult;
import com.goods.mapper.TbItemParamItemMapper;
import com.goods.mapper.TbItemParamMapper;
import com.goods.pojo.TbItemParam;
import com.goods.pojo.TbItemParamExample;
import com.goods.pojo.TbItemParamExample.Criteria;
import com.goods.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public GoodsResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 判断是否查询到结果
		if (list != null && list.size() > 0) {
			return GoodsResult.ok(list.get(0));
		}

		return GoodsResult.ok();
	}

	@Override
	public GoodsResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		// 插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return GoodsResult.ok();
	}

}
