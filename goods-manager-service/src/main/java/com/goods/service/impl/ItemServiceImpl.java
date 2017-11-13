package com.goods.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.common.pojo.EUDatagridResult;
import com.goods.common.utils.ExceptionUtil;
import com.goods.common.utils.GoodsResult;
import com.goods.common.utils.IDUtils;
import com.goods.mapper.TbItemDescMapper;
import com.goods.mapper.TbItemMapper;
import com.goods.mapper.TbItemParamItemMapper;
import com.goods.pojo.TbItem;
import com.goods.pojo.TbItemDesc;
import com.goods.pojo.TbItemExample;
import com.goods.pojo.TbItemExample.Criteria;
import com.goods.pojo.TbItemParamItem;
import com.goods.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		// TbItem item = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/**
	 * 商品列表查询
	 */
	@Override
	public EUDatagridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);

		List<TbItem> list = itemMapper.selectByExample(example);
		EUDatagridResult data = new EUDatagridResult();
		data.setRows(list);
		// 取记录总条数
		PageInfo<TbItem> geInfo = new PageInfo<>(list);
		data.setTotal(geInfo.getTotal());
		return data;
	}

	@Override
	public GoodsResult addItem(TbItem item, TbItemDesc itemDesc, String itemParam) {
		try {
			// 生成商品id
			// 可以使用redis的自增长key，在没有redis之前使用时间+随机数策略生成
			Long itemId = IDUtils.genItemId();
			// 补全不完整的字段
			item.setId(itemId);
			item.setStatus((byte) 1);
			Date date = new Date();
			item.setCreated(date);
			item.setUpdated(date);
			// 把数据插入到商品表
			itemMapper.insert(item);
			// 添加商品描述
			itemDesc.setItemId(itemId);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			// 把数据插入到商品描述表
			itemDescMapper.insert(itemDesc);

			insertItemParamItem(itemId, itemParam);

		} catch (Exception e) {
			e.printStackTrace();
			return GoodsResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		return GoodsResult.ok();

	}

	private GoodsResult insertItemParamItem(Long itemId, String itemParam) {
		// 创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		// 向表中插入数据
		itemParamItemMapper.insert(itemParamItem);

		return GoodsResult.ok();

	}

}
