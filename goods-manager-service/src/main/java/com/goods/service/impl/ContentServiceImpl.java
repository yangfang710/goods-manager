package com.goods.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.goods.common.pojo.EUDatagridResult;
import com.goods.common.utils.GoodsResult;
import com.goods.common.utils.HttpClientUtil;
import com.goods.mapper.TbContentMapper;
import com.goods.pojo.TbContent;
import com.goods.pojo.TbContentExample;
import com.goods.pojo.TbContentExample.Criteria;
import com.goods.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@Override
	public GoodsResult insertContent(TbContent content) {
		// 补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);

		// 添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return GoodsResult.ok();
	}

	@Override
	public EUDatagridResult getContentListById(long categoryId) {
		// 根据parentid查询节点列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		EUDatagridResult data = new EUDatagridResult();
		data.setRows(list);
		// 取记录总条数
		PageInfo<TbContent> geInfo = new PageInfo<>(list);
		data.setTotal(geInfo.getTotal());
		return data;
	}

}
